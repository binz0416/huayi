package com.wicresoft.ghealth.sys.service.impl;

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
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.sys.dao.SystemConfigDao;
import com.wicresoft.ghealth.sys.dto.Config;
import com.wicresoft.ghealth.sys.service.ISystemConfigService;

@Service("systemConfigService")
public class SystemConfigService implements ISystemConfigService {

	@Resource
	private SystemConfigDao sysConfigDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response edit(Config config) throws BusinessException {
		Response rslt = new Response();
		sysConfigDao.updateConfig(config);
	    rslt.success();
		
		return rslt;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response add(Config config) throws BusinessException {
		Response rslt = new Response();
		sysConfigDao.insertConfig(config);
	    rslt.success();
		
		return rslt;
	}
	/**
	 * 获取配置信息
	 * 
	 * @param Map<String,
	 *            Object> list
	 * 
	 */
	@Override
	public Response getConfig() {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();	
		List<Map<String, Object>> configInfoList = sysConfigDao.getConfig();	    
	    ret.put("config", configInfoList);
	    ret.put("totalCount",configInfoList.size());
	    ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_CONFIGCOLUMNS));
		rslt.success(ret);
		return rslt;
	}

}