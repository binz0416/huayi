package com.wicresoft.ghealth.sys.service;


import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.sys.dto.Config;;

public interface ISystemConfigService {

	
	public Response edit(Config record) throws BusinessException;
	
	public Response add(Config record) throws BusinessException;

	public Response getConfig();


}
