package com.wicresoft.ghealth.heart.dao;

import java.util.Map;

import com.wicresoft.ghealth.common.dto.SetMealInfo;

public interface ProjectmanagerDao {

	SetMealInfo getCustomerMealInfos(Map<String, Object> condition);
	
	int checkHasMeal(String customerId);
	
	void insertCustomerMealInfo(SetMealInfo condition);
	
	void updateCustomerMealInfo(SetMealInfo condition);
	
}
