package com.wicresoft.ghealth.acquisition.service;

import java.util.Map;

import com.wicresoft.ghealth.acquisition.dto.Toxin;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface IToxinService {

	public Response getCustomers(Map<String, Object> list) throws BusinessException;

	public Response list(Map<String, Object> list) throws BusinessException;

	public Response save(Toxin record) throws BusinessException;

}
