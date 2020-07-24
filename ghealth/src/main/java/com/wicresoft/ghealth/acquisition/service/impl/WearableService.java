package com.wicresoft.ghealth.acquisition.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.acquisition.dao.AcquisitionDao;
import com.wicresoft.ghealth.acquisition.service.IWearableService;
import com.wicresoft.ghealth.auto.mapper.TActlogMapper;
import com.wicresoft.ghealth.auto.pojo.THeart;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.HttpRequestUtil;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("wearableService")
public class WearableService implements IWearableService {

	@Resource
	private AcquisitionDao acquisitionDao;

	@Resource
	private TActlogMapper actLogDao;

	@Override
	public Response heartbook() {

		Response rslt = new Response();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String connectDate = sdf.format(calendar.getTime());

		List<Map<String, Object>> machines = acquisitionDao.selectMachineByType(CommonConst.TYPE_HEART);

		for (Map<String, Object> machine : machines) {
			String connectResult = String.valueOf(machine.get("connect_result"));
			if (!StringUtils.equals("1", connectResult)) {
				machine.put("connectDate", connectDate);
				this.getHeartBySN(machine);
			}
		}
		rslt.success();
		return rslt;

	}

	@Override
	public Response getHeartBySN(Map<String, Object> machine) {
		Response rslt = new Response();

		String connectDate = String.valueOf(machine.get("connectDate"));
		String machineId = String.valueOf(machine.get("machine_id"));
		String organizationId = String.valueOf(machine.get("organization_id"));
		String roomNum = String.valueOf(machine.get("roomNum"));
		String machineSn = String.valueOf(machine.get("machine_sn"));

		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();

		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) act
				.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		// 事物隔离级别，开启新事务，这样会比较安全些。
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		// 获得事务状态
		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("organizationId", organizationId);
			param.put("roomNum", roomNum);
			param.put("checkinTime", connectDate);

			Map<String, Object> checkin = acquisitionDao.getCustomerId(param);

			if (checkin != null && !checkin.isEmpty()) {
				String customerId = String.valueOf(checkin.get("customer_id"));
				String checkinTime = String.valueOf(checkin.get("checkinTime"));

				if (StringUtils.isNoneBlank(customerId)) {

					String now = String.valueOf((new Date()).getTime());
					String sign = Utils.getMD5(now + CommonConst.HEART_BOOK_KEY + machineSn);

					StringBuffer url = new StringBuffer();
					url.append(CommonConst.HEART_BOOK_URL);
					url.append("?sn=");
					url.append(machineSn);
					url.append("&date=");
					url.append(connectDate);
					url.append("&t=");
					url.append(now);
					url.append("&sign=");
					url.append(sign);

					// String result =
					// HttpRequestUtil.HttpRequest(url.toString());
					String _url = "http://manage.heartbooktech.com:10086/wc/getReport?sn=H100101762311349&date=2016-09-17&t=1474082108&sign=0c5990f3a74b027296c7945dd959f0d1";
					String result = HttpRequestUtil.HttpRequest(_url);

					if (null != result && !"".equals(result)) {
						JSONObject jsonobject = JSONObject.fromObject(result);
						String code = jsonobject.optString("code");
						if (StringUtils.equals(code, "200")) {
							JSONArray array = jsonobject.getJSONArray("data");
							for (int i = 0; i < array.size(); i++) {
								JSONObject object = (JSONObject) array.get(i);
								object.put("checkinTime", checkinTime);
								object.put("customerId", customerId);
								acquisitionDao.addHeart(object);
							}
						} else {
							throw new BusinessException(jsonobject.optString("msg"));
						}
					} else {
						throw new BusinessException("Response is Blank!");
					}
				}
			}

			Map<String, Object> machineLog = new HashMap<String, Object>();

			machineLog.put("machineId", machineId);
			machineLog.put("connectDate", connectDate);
			machineLog.put("connectResult", "1");

			acquisitionDao.saveMachineLog(machineLog);

			transactionManager.commit(status);

			rslt.success();

		} catch (Exception e) {

			transactionManager.rollback(status);

			// 事物隔离级别，开启新事务，这样会比较安全些。
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			// 获得事务状态
			status = transactionManager.getTransaction(def);

			Map<String, Object> machineLog = new HashMap<String, Object>();

			machineLog.put("machineId", machineId);
			machineLog.put("connectDate", connectDate);
			machineLog.put("connectResult", "0");
			machineLog.put("connectLog", e.getMessage());

			acquisitionDao.saveMachineLog(machineLog);

			transactionManager.commit(status);

			rslt.failure();
		}

		return rslt;

	}
}
