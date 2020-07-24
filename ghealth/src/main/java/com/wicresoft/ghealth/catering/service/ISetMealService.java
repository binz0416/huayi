package com.wicresoft.ghealth.catering.service;

import java.util.Map;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface ISetMealService {
	
	public Response camera(Map<String, Object> camera);
	
	public Response refresh(Map<String, Object> list);
	
	public Response getPrintData(Map<String, Object> list);
	
	public Response isPrintOK(Map<String, Object> edit);
	
	public Response getCustomers(Map<String, Object> param) throws BusinessException;
	
	public Response getCustomerMeals(Map<String, Object> param) throws BusinessException;
	
}
