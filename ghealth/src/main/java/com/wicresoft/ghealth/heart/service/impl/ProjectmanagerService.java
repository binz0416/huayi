package com.wicresoft.ghealth.heart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.CustomerCheckinInfo;
import com.wicresoft.ghealth.common.dto.CustomerInfo;
import com.wicresoft.ghealth.common.dto.SetMealInfo;
import com.wicresoft.ghealth.heart.dao.ProjectmanagerDao;
import com.wicresoft.ghealth.heart.service.IProjectmanagerService;

@Service("projectmanagerService")
public class ProjectmanagerService implements IProjectmanagerService {
	
	@Resource
	private CommonDao commonDao;
	
	@Resource
	private ProjectmanagerDao projectmanagerDao;
	
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
	public Response getCustomerMealInfos(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		SetMealInfo customerMealInfos = this.projectmanagerDao.getCustomerMealInfos(list);
		if (customerMealInfos == null) {
			customerMealInfos = new SetMealInfo();
		}
		ret.put("customerMealInfos", customerMealInfos);
		rslt.success(ret);
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response save(SetMealInfo mealInfo) throws BusinessException {
		Response rslt = new Response();
		int hasMeal = this.projectmanagerDao.checkHasMeal(mealInfo.getCustomerId());
		if (hasMeal > 0) {
			this.projectmanagerDao.updateCustomerMealInfo(mealInfo);
		}else {
			this.projectmanagerDao.insertCustomerMealInfo(mealInfo);
		}
		rslt.success(mealInfo);
		return rslt;
	}


}
