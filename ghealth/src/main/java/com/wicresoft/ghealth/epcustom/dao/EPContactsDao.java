package com.wicresoft.ghealth.epcustom.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.auto.pojo.TRContactsEPCustomer;
import com.wicresoft.ghealth.epcustom.dto.EPContactsDto;
import com.wicresoft.ghealth.epcustom.dto.EPCustomerDto;

public interface EPContactsDao {
	
    // 获取联系人信息
    List<EPContactsDto> getEPContacts(Map<String, Object> para);
    
    // 联系人总数
 	int countEPContacts(Map<String, Object> para);
 	
 	// 获取客户下的联系人信息
    List<EPContactsDto> getEPContactsByCustom(Map<String, Object> para);
    
 	// 判断是否有该联系人
 	int checkHasEPContacts(Long contactsId);
    
    // 新增联系人信息
 	int insertEPContactsInfo(EPContactsDto condition);
 	
  	// 更新联系人信息
 	void updateEPContactsInfo(EPContactsDto condition);
 	
 	// 删除联系人信息
  	//void deleteEPContactsInfo(Long contactsId);
  	
  	// 更新联系人信息
   	void updateContactsByCustom(EPContactsDto condition);
 	
 	// 新增客户-联系人关联信息
 	void insertEPCustomerContactsInfo(TRContactsEPCustomer condition);
 	
  	// 更新客户-联系人关联信息
 	void updateEPCustomerContactsInfo(TRContactsEPCustomer condition);
 	
 	// 删除客户-联系人关联信息
  	void deleteRelationByContacts(Long contactsId);
  	
  	// 删除客户-联系人关联信息
   	void deleteRelationByCustomer(Long epcustomerId);
    

}
