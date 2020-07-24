package com.wicresoft.ghealth.epcustom.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.auto.pojo.TEPCustomer;
import com.wicresoft.ghealth.auto.pojo.TEPCustomerData;
import com.wicresoft.ghealth.epcustom.dto.EPCustomerDto;

public interface EPCustomDao {
	
    // 获取企业客户
    List<EPCustomerDto> getEPCustomerInfos(Map<String, Object> para);
    
    // 客户总数
 	int countEPCustomers(Map<String, Object> para);
 	
 	// 判断是否有该客户
 	int checkHasEPCustomer(Long epcustomerId);
 	
 	// 新增客户信息
 	int insertEPCustomerInfo(EPCustomerDto condition);
	
 	// 更新客户信息
	void updateEPCustomerInfo(EPCustomerDto condition);
	
	// 更新客户信息
	void updateEPCustomerByContactsId(Long contactsId);
	
	// 设置首要联系人
	void updateEPCustomerSetFirst(TEPCustomer epCustomer);
	
	// 删除客户信息
	//void deleteEPCustomerInfo(Long epcustomerId);
	
	// 新增附加信息
	void insertEPCustomerDataInfo(TEPCustomerData condition);
	
 	// 更新附加信息
	void updateEPCustomerDataInfo(TEPCustomerData condition);
	
	// 删除附加信息
	void deleteEPCustomerDataInfo(Long epcustomerId);
	
	// 客户统计报表
	List<EPCustomerDto> getCustomtj(Map<String, Object> para);
	
	// 客户员工数统计
	List<Map<String, Object>> getCustomTJygs(Map<String, Object> para);
	
	// 客户来源、营业额、行业统计
	List<Map<String, Object>> getCustomTJOther(Map<String, Object> para);
}
