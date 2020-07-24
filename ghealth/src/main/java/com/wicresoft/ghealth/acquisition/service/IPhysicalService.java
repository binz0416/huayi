package com.wicresoft.ghealth.acquisition.service;

import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.PhysicalDto;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface  IPhysicalService {
	
	public Response list(Map<String, Object> list) throws BusinessException;

	public Response edit(PhysicalDto physicalDto) throws BusinessException;

	public Response dele(Map<String, Object> dele) throws BusinessException;
	
	public Response getCustomers(Map<String, Object> list) throws BusinessException;

}
