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

import com.wicresoft.ghealth.acquisition.dao.ToxinDao;
import com.wicresoft.ghealth.acquisition.dto.Toxin;
import com.wicresoft.ghealth.acquisition.dto.ToxinAbnormal;
import com.wicresoft.ghealth.acquisition.dto.ToxinDetected;
import com.wicresoft.ghealth.acquisition.service.IToxinService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;
import com.wicresoft.ghealth.common.dto.CustomerInfo;

@Service("toxinService")
public class ToxinService implements IToxinService {

	@Resource
	private ToxinDao toxinDao;

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
		Map<String, Object> toxin = toxinDao.getToxinReport(list);
		if (toxin == null) {
			toxin = new HashMap<String, Object>();
		}
		rslt.success(toxin);
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response save(Toxin record) throws BusinessException {
		// ApplicationContext act =
		// ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		// TODO: 新增记录还是更新记录
		Long toxinId = this.toxinDao.getToxinId(record);
		// Toxin update
		if (toxinId != null) {
			// main key set
			record.setToxinId(toxinId);
			this.toxinDao.updateToxin(record);
			this.toxinDao.deleteToxinDetected(toxinId);
			this.toxinDao.deleteToxinAbnormal(toxinId);
			if(record.getToxinDetected().size()>0){
				for(int i=0;i< record.getToxinDetected().size();i++){
					ToxinDetected detectedRecord = record.getToxinDetected().get(i);
					detectedRecord.setToxinId(toxinId);
					this.toxinDao.insertDetected(detectedRecord);
				}	
			}
			if(record.getToxinAbnormal().size()>0){
				for(int i=0;i< record.getToxinAbnormal().size();i++){
					ToxinAbnormal abnormalRecord = record.getToxinAbnormal().get(i);
					abnormalRecord.setToxinId(toxinId);
					this.toxinDao.insertAbnormal(abnormalRecord);
				}
			}	
		}
		// Toxin insert
		else {
			record.setInspectionTime(new Date());;
			this.toxinDao.insertToxin(record);
			toxinId = record.getToxinId();
			if(record.getToxinDetected().size()>0){
				for(int i=0;i< record.getToxinDetected().size();i++){
					ToxinDetected detectedRecord = record.getToxinDetected().get(i);
					detectedRecord.setToxinId(toxinId);
					this.toxinDao.insertDetected(detectedRecord);
				}	
			}
			if(record.getToxinAbnormal().size()>0){
				for(int i=0;i< record.getToxinDetected().size();i++){
					ToxinAbnormal abnormalRecord = record.getToxinAbnormal().get(i);
					abnormalRecord.setToxinId(toxinId);
					this.toxinDao.insertAbnormal(abnormalRecord);
				}
			}

			// 插入档案详细信息
			String archivesId = this.commonDao.getArchiveId(record.getCustomerId());
			ArchivesDetailDto detail = new ArchivesDetailDto();
			detail.setArchivesId(archivesId);
			detail.setInspectionTime(record.getInspectionTime());
			detail.setScId(record.getToxinId());
			detail.setType(Integer.parseInt(CommonConst.ARCHIVES_TYPE_TOXIN));
			this.commonDao.insertArchivesDetail(detail);
		}
		rslt.success();
		return rslt;
	}

}
