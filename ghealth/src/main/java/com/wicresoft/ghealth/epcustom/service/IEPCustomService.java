package com.wicresoft.ghealth.epcustom.service;

import java.util.Map;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.epcustom.dto.EPContactsDto;
import com.wicresoft.ghealth.epcustom.dto.EPCustomerDto;

public interface IEPCustomService {

	// 企业客户信息start
	// 获取企业客户信息
	public Response selectAllEPCustoms(Map<String, Object> list) throws BusinessException;
	
 	// 更新客户信息
	public Response editEPCustomerInfo(EPCustomerDto condition) throws BusinessException;
	
	// 删除客户信息
	public Response deleEPCustomerInfo(Map<String, Object> list) throws BusinessException;
		
	// 设置首要联系人
	public Response setFirst(Map<String, Object> list) throws BusinessException;
	
	// 客户统计
	public Response getCustomTjInfo(Map<String, Object> list) throws BusinessException;
	
	// 企业客户信息end
	
	// 联系人信息start
	// 获取联系人信息
	public Response selectAllEPContacts(Map<String, Object> list) throws BusinessException;
	
  	// 更新联系人信息
	public Response editEPContactsInfo(EPContactsDto condition) throws BusinessException;
	
	// 删除联系人信息
	public Response deleEPContactsInfo(Map<String, Object> list) throws BusinessException;
	
	
	// 联系人信息end
}
