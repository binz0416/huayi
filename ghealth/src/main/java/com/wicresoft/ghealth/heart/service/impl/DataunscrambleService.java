package com.wicresoft.ghealth.heart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.ContextLoader;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;
import com.wicresoft.ghealth.common.dto.CustomerCheckinInfo;
import com.wicresoft.ghealth.common.dto.CustomerInfo;
import com.wicresoft.ghealth.heart.dao.DataunscrambleDao;
import com.wicresoft.ghealth.heart.service.IDataunscrambleService;

@Service("dataunscrambleService")
public class DataunscrambleService implements IDataunscrambleService {

	@Resource
	private DataunscrambleDao dataunscrambleDao;

	@Resource
	private CommonDao commonDao;

	@Override
	public Response getCustomers(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.commonDao.countCustomers(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<CustomerInfo> customerList = this.commonDao.getCustomerInfos(list);
			for (CustomerInfo customer : customerList) {
				List<CustomerCheckinInfo> checkinInfos = customer.getCheckinInfos();
				if (checkinInfos != null && checkinInfos.size() > 0) {
					customer.setCustomerLastCheckin(checkinInfos.get(0).getCustomerCheckin());
					customer.setCustomerLastCheckinDsp(checkinInfos.get(0).getCustomerCheckinDsp());
				}

			}
			ret.put("checkinInfos", customerList);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_LEFTCUSTOMERS));
		rslt.success(ret);
		return rslt;
	}
	
	@Override
	public Response list(@RequestBody Map<String, Object> list) {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		return rslt;
	}

	@Override
	public Response init(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		String customerId = list.get("customerId").toString();
		
		Map<String, Object> archiveInfos = this.dataunscrambleDao.getArchiveInfos(customerId);
		if (archiveInfos != null) {
			
			ret.put("archivesId", archiveInfos.get("archives_id").toString());
			ret.put("advise", archiveInfos.get("advise").toString());
			
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("customerId", customerId);
			condition.put("type", "");
			
			// 穿戴式
			condition.replace("type", CommonConst.ARCHIVES_TYPE_HEART);
			Map<String, Object> wearableData = this.dataunscrambleDao.getHeartInfos(condition);
			if (wearableData != null) {
				this.setValue(CommonConst.ARCHIVES_TYPE_HEART, wearableData);
				ret.put("wearableData", wearableData);
			}
			
			// 体检-血检
			condition.replace("type", CommonConst.ARCHIVES_TYPE_BLOOD);
			Map<String, Object> bloodData = this.dataunscrambleDao.getPhysicalBloodInfos(condition);
			if (bloodData != null) {
				this.setValue(CommonConst.ARCHIVES_TYPE_BLOOD, bloodData);
				ret.put("bloodData", bloodData);
			}
			
			// 体检-超声
			condition.replace("type", CommonConst.ARCHIVES_TYPE_ULTRASOUND);
			Map<String, Object> ultrasoundData = this.dataunscrambleDao.getPhysicalUltrasoundInfos(condition);
			if (ultrasoundData != null) {
				this.setValue(CommonConst.ARCHIVES_TYPE_ULTRASOUND, ultrasoundData);
				ret.put("ultrasoundData", ultrasoundData);
			}
			
			// 体检-尿检
			condition.replace("type", CommonConst.ARCHIVES_TYPE_URINE);
			Map<String, Object> urineData = this.dataunscrambleDao.getPhysicalUrineInfos(condition);
			if (urineData != null) {
				this.setValue(CommonConst.ARCHIVES_TYPE_URINE, urineData);
				ret.put("urineData", urineData);
			}		
					
			// 太空舱
			condition.replace("type", CommonConst.ARCHIVES_TYPE_SPACECAPSULE);
			Map<String, Object> spacecapsuleData = this.dataunscrambleDao.getSpacecapsuleInfos(condition);
			if (spacecapsuleData != null) {
				this.setValue(CommonConst.ARCHIVES_TYPE_SPACECAPSULE, spacecapsuleData);
				ret.put("spacecapsuleData", spacecapsuleData);
			}
			
		}else {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("I0002", null, Locale.getDefault()));
			rslt.failure(errMap);
		}
		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response add(Map<String, Object> add) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response edit(ArchivesDetailDto detailDto) throws BusinessException {
		Response rslt = new Response();
		if (detailDto != null) {
			if (detailDto.getArchivesDetailId() != null) {
				this.dataunscrambleDao.updateArchivesDetail(detailDto);
			}else {
				this.dataunscrambleDao.insertArchivesDetail(detailDto);
			}
		}

		rslt.success();
		return rslt;
	}

	@Override
	public Response dele(Map<String, Object> dele) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response getEcgData(Map<String, Object> condition) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		condition.put("type", CommonConst.ARCHIVES_TYPE_HEART);
		Map<String, Object> urlMap = this.dataunscrambleDao.getUrl(condition);
		if (urlMap != null) {
			List<Integer> ecgData = Utils.ecgRead(urlMap.get("url").toString(), urlMap.get("uuid").toString());
			ret.put("ecgData", ecgData);
			rslt.success(ret);
		}else {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("I0002", null, Locale.getDefault()));
			rslt.failure(errMap);
		}
		
		
		return rslt;
	}
	
	private Map<String, Object> setValue(String type, Map<String, Object> objMap){
		if (!objMap.containsKey("detailId")) {
			objMap.put("detailId", null);
		}
		if (!objMap.containsKey("detailAdvise")) {
			objMap.put("detailAdvise", null);
		}
		if (!objMap.containsKey("type")) {
			objMap.put("type", type);
		}
		
		return objMap;
	}
}
