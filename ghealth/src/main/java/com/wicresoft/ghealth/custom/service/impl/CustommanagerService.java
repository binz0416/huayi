package com.wicresoft.ghealth.custom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.wicresoft.ghealth.auto.mapper.TCustomerCheckinMapper;
import com.wicresoft.ghealth.auto.mapper.TCustomerMapper;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.CustomerCheckinInfo;
import com.wicresoft.ghealth.common.dto.CustomerInfo;
import com.wicresoft.ghealth.custom.dao.CustomDao;
import com.wicresoft.ghealth.custom.service.ICustommanagerService;

@Service("custommanagerService")
public class CustommanagerService implements ICustommanagerService {

	@Resource
	private CommonDao commonDao;

	@Resource
	private CustomDao customDao;

	@Resource
	private TCustomerMapper customerMapper;

	@Resource
	private TCustomerCheckinMapper checkinMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response hotel(Map<String, Object> hotel) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
//		TCheckin checkin = new TCheckin();
//		String organizationNo = (String) hotel.get("organizationNo");
//		TOrganization organization = customDao.selectByOrganizationId(organizationNo);
//		if (organization != null) {
//			checkin.setOrganizationId(organization.getOrganizationId());
//			checkin.setRoomnum((String) hotel.get("roomNum"));
//			Date checkinTime = Utils.getLongToDate(Long.parseLong((String) hotel.get("checkinTime")), "yyyy-MM-dd");
//			checkin.setCheckintime(checkinTime);
//			String checkoutTime = (String) hotel.get("checkoutTime");
//			if (checkoutTime != null && checkoutTime != "") {
//				checkin.setCheckouttime(Utils.getStringToDate(checkoutTime, "yyyy-MM-dd"));
//			} else {
//				checkin.setCheckouttime(Utils.getSecondDay(checkinTime));
//			}
//			checkin.setFaceId((Integer) hotel.get("faceId"));
//			checkin.setIdcard((String) hotel.get("idcard"));
//			checkin.setName((String) hotel.get("name"));
//			checkin.setCustomerId((String) hotel.get("customerId"));
//			checkin.setMobile((String) hotel.get("mobile"));
//
//			/*
//			 * TCustomer customerInfo = new TCustomer();
//			 * customerInfo.setFaceId((Integer) hotel.get("faceId"));
//			 * customerInfo.setNo((String) hotel.get("no"));
//			 * customerInfo.setIdcard((String) hotel.get("idcard"));
//			 * customerInfo.setName((String) hotel.get("name"));
//			 * customerInfo.setGender((String) hotel.get("gender"));
//			 * customerInfo.setBirthday((String) hotel.get("birthday"));
//			 * customerInfo.setPhoto((String) hotel.get("photo"));
//			 * customerInfo.setBirthplace((String) hotel.get("birthplace"));
//			 * customerInfo.setUsualaddress((String) hotel.get("usualaddress"));
//			 * customerInfo.setEthnic((String) hotel.get("ethnic"));
//			 * customerInfo.setCulture((String) hotel.get("culture"));
//			 * customerInfo.setProfessions((String) hotel.get("professions"));
//			 * customerInfo.setIncome((String) hotel.get("income"));
//			 * customerInfo.setMarriage((String) hotel.get("marriage"));
//			 * customerInfo.setWorkunits((String) hotel.get("workunits"));
//			 * customerInfo.setDuty((String) hotel.get("duty"));
//			 * customerInfo.setUnitsphone((String) hotel.get("unitsphone"));
//			 * customerInfo.setAddress((String) hotel.get("address"));
//			 * customerInfo.setPhone((String) hotel.get("phone"));
//			 * customerInfo.setMobile((String) hotel.get("mobile"));
//			 * customerInfo.setMedicalexpenses((String)
//			 * hotel.get("medicalexpenses"));
//			 * 
//			 * String idcard = (String) hotel.get("idcard"); TCustomer customer
//			 * = customDao.selectByIdcard(idcard); String customerId = ""; if
//			 * (customer != null) {
//			 * customerInfo.setCustomerId(customer.getCustomerId());
//			 * customerMapper.updateByPrimaryKeySelective(customerInfo);
//			 * customerId = customer.getCustomerId(); } else {
//			 * customerMapper.insert(customerInfo); TCustomer customerNew =
//			 * customDao.selectByIdcard(idcard); customerId =
//			 * customerNew.getCustomerId(); }
//			 * 
//			 * checkin.setCustomerId(customerId);
//			 */
//			checkinMapper.insert(checkin);
//			rslt.success();
//		} else {
//			rslt.failure(act.getMessage("E9016", null, Locale.getDefault()));
//		}
		return rslt;
	}

	@Override
	public Response selectAll(Map<String, Object> list) {
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
			ret.put("customerList", customerList);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_CUSTOMMANAGER));
		rslt.success(ret);
		return rslt;
	}

}
