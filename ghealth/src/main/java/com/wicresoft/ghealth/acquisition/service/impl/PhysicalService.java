package com.wicresoft.ghealth.acquisition.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.wicresoft.ghealth.acquisition.dao.AcquisitionDao;
import com.wicresoft.ghealth.acquisition.dto.PhysicalBloodDto;
import com.wicresoft.ghealth.acquisition.dto.PhysicalDto;
import com.wicresoft.ghealth.acquisition.dto.PhysicalUltrasoundDto;
import com.wicresoft.ghealth.acquisition.dto.PhysicalUrineDto;
import com.wicresoft.ghealth.acquisition.service.IPhysicalService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.CustomerInfo;

@Service("physicalService")
public class PhysicalService implements IPhysicalService {

	@Resource
	private AcquisitionDao acquisitionDao;

	@Resource
	private CommonDao commonDao;

	@Override
	public Response list(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> physicalMap = new HashMap<String, Object>();
		PhysicalBloodDto bloodData = acquisitionDao.getPhysicalBloodInfos(list);
		if (bloodData == null) {
			bloodData = new PhysicalBloodDto();
		}
		physicalMap.put("bloodData", bloodData);
		
		PhysicalUltrasoundDto ultrasoundData = acquisitionDao.getPhysicalUltrasoundInfos(list);
		if (ultrasoundData == null) {
			ultrasoundData = new PhysicalUltrasoundDto();
		}
		physicalMap.put("ultrasoundData", ultrasoundData);
		
		PhysicalUrineDto urineData = acquisitionDao.getPhysicalUrineInfos(list);
		if (urineData == null) {
			urineData = new PhysicalUrineDto();
		}
		physicalMap.put("urineData", urineData);
		
		rslt.success(physicalMap);
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response edit(PhysicalDto physicalDto) throws BusinessException {
		Response rslt = new Response();
		PhysicalBloodDto bloodData = physicalDto.getBloodData();
		boolean bloodIsNull = Utils.checkObjFieldIsNull(bloodData);
		if (!bloodIsNull) {
			if (bloodData.getBloodId() != null) {
				this.acquisitionDao.updatePhysicalBlood(bloodData);
			}else {
				bloodData.setCustomerId(physicalDto.getCustomerId());
				this.acquisitionDao.insertPhysicalBlood(bloodData);
			}
		}
		
		PhysicalUltrasoundDto ultrasoundData = physicalDto.getUltrasoundData();
		boolean ultrasoundIsNull = Utils.checkObjFieldIsNull(ultrasoundData);
		if (!ultrasoundIsNull) {
			if (ultrasoundData.getUltrasoundId() != null) {
				this.acquisitionDao.updatePhysicalUltrasound(ultrasoundData);
			}else {
				ultrasoundData.setCustomerId(physicalDto.getCustomerId());
				this.acquisitionDao.insertPhysicalUltrasound(ultrasoundData);
			}
		}
		
		PhysicalUrineDto urineData = physicalDto.getUrineData();
		boolean urineIsNull = Utils.checkObjFieldIsNull(urineData);
		if (!urineIsNull) {
			if (urineData.getUrineId() != null) {
				this.acquisitionDao.updatePhysicalUrine(urineData);
			}else {
				urineData.setCustomerId(physicalDto.getCustomerId());
				this.acquisitionDao.insertPhysicalUrine(urineData);
			}
		}
		
		if (bloodIsNull && ultrasoundIsNull && urineIsNull) {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("I0003", null, Locale.getDefault()));
			rslt.failure(errMap);
		}else {
			rslt.success();
		}
		
		return rslt;
	}

	@Override
	public Response dele(Map<String, Object> dele) throws BusinessException {
		Response rslt = new Response();
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Map<String, Object> ret = new HashMap<String, Object>();
		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response getCustomers(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.commonDao.countCustomers(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<CustomerInfo> checkinInfos = this.commonDao.getCustomerInfos(list);
			ret.put("checkinInfos", checkinInfos);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_LEFTCUSTOMERS));
		rslt.success(ret);
		return rslt;
	}

}
