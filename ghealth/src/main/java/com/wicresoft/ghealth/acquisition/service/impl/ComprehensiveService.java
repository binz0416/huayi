package com.wicresoft.ghealth.acquisition.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wicresoft.ghealth.acquisition.dao.ComprehensiveDao;
import com.wicresoft.ghealth.acquisition.dto.Comprehensive;
import com.wicresoft.ghealth.acquisition.service.IComprehensiveService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;
import com.wicresoft.ghealth.common.dto.CustomerInfo;

@Service("comprehensiveService")
public class ComprehensiveService implements IComprehensiveService {

	@Resource
	private CommonDao commonDao;
	
	@Resource
	private ComprehensiveDao comprehensiveDao;

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

	@Override
	public Response list(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> comprehensive = comprehensiveDao.getComprehensiveReport(list);
		if (comprehensive == null) {
			comprehensive = new HashMap<String, Object>();
		}
		rslt.success(comprehensive);
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response save(Comprehensive record) throws BusinessException {
		// ApplicationContext act =
		// ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		if (record.getComprehensiveId() != null) {
			comprehensiveDao.updateComprehensive(record);
		}else {
			record.setInspectionTime(new Date());
			comprehensiveDao.insertComprehensive(record);
			String archivesId = this.commonDao.getArchiveId(record.getCustomerId());
			ArchivesDetailDto detail = new ArchivesDetailDto();
			detail.setArchivesId(archivesId);
			detail.setInspectionTime(record.getInspectionTime());
			detail.setScId(record.getComprehensiveId());
			detail.setType(Integer.parseInt(CommonConst.ARCHIVES_TYPE_COMPREHENSIVE));
			this.commonDao.insertArchivesDetail(detail);
		}
		rslt.success();
		return rslt;
	}
}
