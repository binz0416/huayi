package com.wicresoft.ghealth.heart.service;

import java.util.Map;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.dto.SetMealInfo;

public interface IProjectmanagerService {
	
	public Response getCustomers(Map<String, Object> list) throws BusinessException;
	
	public Response getCustomerMealInfos(Map<String, Object> list) throws BusinessException;

	public Response save(SetMealInfo mealInfo) throws BusinessException;


}
