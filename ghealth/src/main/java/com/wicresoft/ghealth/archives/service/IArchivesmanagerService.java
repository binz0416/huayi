package com.wicresoft.ghealth.archives.service;

import java.util.Map;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface IArchivesmanagerService {

	public Response reportCreate(Map<String, Object> report) throws BusinessException;
	
	public Response getCustomers(Map<String, Object> list) throws BusinessException;
	
	public Response getUserArchives(Map<String, Object> list) throws BusinessException;
	
	public Response getEcgData(Map<String, Object> condition) throws BusinessException;
	
	public Map<String, Object> getCustArchiveInfo(Map<String, Object> list) throws BusinessException;
	
	public Map<String, Object> getCustArchiveInfoForReport(Map<String, Object> list) throws BusinessException;
	
}
