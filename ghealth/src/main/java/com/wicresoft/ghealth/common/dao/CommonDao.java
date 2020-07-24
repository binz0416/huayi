package com.wicresoft.ghealth.common.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.auto.pojo.TActlog;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;
import com.wicresoft.ghealth.common.dto.CustomerInfo;
import com.wicresoft.ghealth.common.dto.UserInfo;

public interface CommonDao {

	// 系统用户情报
	UserInfo getUserInfo(Map<String, Object> para);

	// 操作历史记录
	int insertAndGetId(TActlog record);

	// 用户所属机构
	List<Map<String, Object>> getOrganizations();

	// 权限_Roles
	List<Map<String, Object>> getRoles();

	// 权限_Actions
	List<Map<String, Object>> getActions();
	
	// 客户count
	int countCustomers(Map<String, Object> para);
	
	// 获取客户
	List<CustomerInfo> getCustomerInfos(Map<String, Object> para);
	
	String getArchiveId(String customer);
	
	void insertArchivesDetail(ArchivesDetailDto para);
	
	Map<String, Object> getCustomerInfo(Map<String, Object> para);
}