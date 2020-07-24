package com.wicresoft.ghealth.acquisition.service;

import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.Comprehensive;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface IComprehensiveService {

	public Response getCustomers(Map<String, Object> list) throws BusinessException;

	public Response list(Map<String, Object> list) throws BusinessException;

	public Response save(Comprehensive record) throws BusinessException;

}
