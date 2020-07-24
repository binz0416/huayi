package com.wicresoft.ghealth.epcustom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wicresoft.ghealth.auto.pojo.TEPCustomer;
import com.wicresoft.ghealth.auto.pojo.TEPCustomerData;
import com.wicresoft.ghealth.auto.pojo.TRContactsEPCustomer;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.epcustom.dao.EPContactsDao;
import com.wicresoft.ghealth.epcustom.dao.EPCustomDao;
import com.wicresoft.ghealth.epcustom.dto.EPContactsDto;
import com.wicresoft.ghealth.epcustom.dto.EPCustomerDto;
import com.wicresoft.ghealth.epcustom.service.IEPCustomService;
import com.wicresoft.ghealth.sys.dao.SystemUsersDao;

@Service("epCustomService")
public class EPCustomService implements IEPCustomService {
	
	@Resource
	private EPCustomDao epCustomDao;
	
	@Resource
	private EPContactsDao epContactsDao;
	
	@Resource
	private SystemUsersDao sysUsersDao;

	// 企业客户信息start
	@Override
	public Response selectAllEPCustoms(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.epCustomDao.countEPCustomers(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			// 企业客户信息
			List<EPCustomerDto> epCustomerList = new ArrayList<EPCustomerDto>();
			epCustomerList = this.epCustomDao.getEPCustomerInfos(list);
			
			for (EPCustomerDto epCustomerDto : epCustomerList) {
				Map<String, Object> para = new HashMap<String, Object>();
				para.put("creatorUserid", epCustomerDto.getOwnerUserid());
				para.put("epcustomerId", epCustomerDto.getEpcustomerId());
				List<EPContactsDto> contactsList = this.epContactsDao.getEPContactsByCustom(para);
				if (contactsList == null || contactsList.size() == 0) {
					contactsList = new ArrayList<EPContactsDto>();
				}
				epCustomerDto.setContactsList(contactsList);
			}
			ret.put("epCustomerList", epCustomerList);
		}
		// 用户
		List<Map<String, Object>> userInfoList = sysUsersDao.getUsers(list);
		ret.put("userInfoList", userInfoList);
		// 显示列名
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_EPCUSTOMMANAGER));
		// 企业客户查看联系人画面显示列
		ret.put("cxcontactscolumns", Utils.getColumns(CommonConst.PAGE_FLG_CXCONTACTSCOLUMNS));
		// 当前用户
		ret.put("currentUserid", Long.valueOf(list.get("ownerUserid").toString()));

		rslt.success(ret);
		return rslt;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response editEPCustomerInfo(EPCustomerDto condition) throws BusinessException {
		Response rslt = new Response();
		int hasCustom = this.epCustomDao.checkHasEPCustomer(condition.getEpcustomerId());
		
		if (hasCustom > 0) {
			condition.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			this.epCustomDao.updateEPCustomerInfo(condition);
			TEPCustomerData customData = new TEPCustomerData();
			customData.setEpcustomerId(condition.getEpcustomerId());
			customData.setNoOfEmployees(condition.getNoOfEmployees());
			customData.setDescription(condition.getDescription());
			this.epCustomDao.updateEPCustomerDataInfo(customData);
		}else {
			condition.setContactsId(Long.valueOf(0));
			condition.setCreateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			condition.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			condition.setIsDeleted(0);
			condition.setIsLocked(0);
			condition.setDeleteUserid(Long.valueOf(0));
			condition.setDeleteTime(0);
			this.epCustomDao.insertEPCustomerInfo(condition);
			Long epcustomerId = condition.getEpcustomerId();
			
			TEPCustomerData customData = new TEPCustomerData();
			customData.setEpcustomerId(epcustomerId);
			customData.setNoOfEmployees(condition.getNoOfEmployees());
			customData.setDescription(condition.getDescription());
			this.epCustomDao.insertEPCustomerDataInfo(customData);
		}
		rslt.success(condition);
		return rslt;
		
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response deleEPCustomerInfo(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Long epcustomerId = Long.valueOf(list.get("epcustomerId").toString());
		Long userId = Long.valueOf(list.get("userId").toString());
		
		EPCustomerDto customerDto = new EPCustomerDto();
		customerDto.setEpcustomerId(epcustomerId);
		customerDto.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		customerDto.setIsDeleted(1);
		customerDto.setDeleteUserid(userId);
		customerDto.setDeleteTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		this.epCustomDao.updateEPCustomerInfo(customerDto);
		//this.epCustomDao.deleteEPCustomerDataInfo(epcustomerId);
		
		EPContactsDto contactsDto = new EPContactsDto();
		contactsDto.setEpcustomerId(epcustomerId);
		contactsDto.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		contactsDto.setIsDeleted(1);
		contactsDto.setDeleteUserid(userId);
		contactsDto.setDeleteTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		this.epContactsDao.updateContactsByCustom(contactsDto);
		//this.epContactsDao.deleteRelationByCustomer(epcustomerId);
		
		rslt.success("success");
		return rslt;
	}
	
	@Override
	public Response setFirst(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Long epcustomerId = Long.valueOf(list.get("epcustomerId").toString());
		Long contactsId = Long.valueOf(list.get("contactsId").toString());
		
		TEPCustomer epCustomer = new TEPCustomer();
		epCustomer.setEpcustomerId(epcustomerId);
		epCustomer.setContactsId(contactsId);
		epCustomer.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		
		this.epCustomDao.updateEPCustomerSetFirst(epCustomer);
		
		rslt.success("success");
		return rslt;
	}
	
	@Override
	public Response getCustomTjInfo(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		String[] tlList = new String[10];
		String type = list.get("type").toString();
		Integer tjTotal = 0;
		Integer fzTotal = 0;
		String title = "";
		int total = 0;
		
		if ("1".equals(type)) {
			List<EPCustomerDto> infoList = this.epCustomDao.getCustomtj(list);
			if (infoList == null || infoList.size() == 0) {
				infoList = new ArrayList<EPCustomerDto>();
			}else {
				for (EPCustomerDto epCustomerDto : infoList) {
					if (epCustomerDto.getCreatCount() != null) {
						tjTotal = tjTotal + epCustomerDto.getCreatCount();
					}else {
						epCustomerDto.setCreatCount(0);
					}
					if (epCustomerDto.getOwnerCount() != null) {
						fzTotal = fzTotal + epCustomerDto.getOwnerCount();
					}else {
						epCustomerDto.setOwnerCount(0);
					}
					
				}
			}
			ret.put("dataList", infoList);
			
			
		}else if ("3".equals(type)) {
			dataList = this.epCustomDao.getCustomTJygs(list);
		}else {
			dataList = this.epCustomDao.getCustomTJOther(list);
		}
		
		if (!"1".equals(type)) {
			
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					total = total + Integer.parseInt(dataList.get(i).get("value").toString());
				}
			}
			if ("2".equals(type)) {
				
				tlList = Utils.getArray(CommonConst.PAGE_ORIGINTL);
				title = "客户来源统计(共计" + total + "条)";
			} else if ("3".equals(type)) {
				tlList = Utils.getArray(CommonConst.PAGE_NOOFEMPLOYEESTL);
				title = "客户员工数统计(共计" + total + "条)";
			} else if ("4".equals(type)) {
				tlList = Utils.getArray(CommonConst.PAGE_ANNUALREVENUETL);
				title = "客户营业额统计(共计" + total + "条)";
			} else if ("5".equals(type)) {
				tlList = Utils.getArray(CommonConst.PAGE_INDUSTRYTL);
				title = "客户行业统计(共计" + total + "条)";
			}
			
			// 客户统计数据
			ret.put("dataList", dataList);
		}
		
		// 添加客户总数
		ret.put("tjTotal", tjTotal);
		// 有人负责总数
		ret.put("fzTotal", fzTotal);
		// 统计图标题
		ret.put("title", title);
		// 统计图图例
		ret.put("tlList", tlList);
		// 客户统计报表列
		ret.put("customtjcolumns", Utils.getColumns(CommonConst.PAGE_CUSTOMTJCOLUMNS));
		// 用户
		List<Map<String, Object>> userInfoList = sysUsersDao.getUsers(list);
		ret.put("userInfoList", userInfoList);
		
		rslt.success(ret);
		return rslt;
	}
	
	
	// 企业客户信息end

	// 联系人信息start
	@Override
	public Response selectAllEPContacts(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.epContactsDao.countEPContacts(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			// 联系人信息
			List<EPContactsDto> epContactsList = this.epContactsDao.getEPContacts(list);
			ret.put("epContactsList", epContactsList);
		}
		// 联系人画面显示列
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_ALLCONTACTSCOLUMNS));
		
		// 企业客户信息
		List<EPCustomerDto> epCustomerList = new ArrayList<EPCustomerDto>();
		epCustomerList = this.epCustomDao.getEPCustomerInfos(list);
		ret.put("epCustomerList", epCustomerList);

		rslt.success(ret);
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	@Override
	public Response editEPContactsInfo(EPContactsDto condition) throws BusinessException {
		Response rslt = new Response();
		int hasContacts = this.epContactsDao.checkHasEPContacts(condition.getContactsId());
		
		if (hasContacts > 0) {
			condition.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			this.epContactsDao.updateEPContactsInfo(condition);
		}else {
			
			condition.setCreateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			condition.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
			condition.setIsDeleted(0);
			condition.setDeleteUserid(Long.valueOf(0));
			condition.setDeleteTime(0);
			this.epContactsDao.insertEPContactsInfo(condition);
			Long epContactsId = condition.getContactsId();
			
			TRContactsEPCustomer tr = new TRContactsEPCustomer();
			tr.setContactsId(epContactsId);
			tr.setEpcustomerId(condition.getEpcustomerId());
			this.epContactsDao.insertEPCustomerContactsInfo(tr);
		}
		rslt.success(condition);
		return rslt;
	}

	@Override
	public Response deleEPContactsInfo(Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Long contactsId = Long.valueOf(list.get("contactsId").toString());
		Long userId = Long.valueOf(list.get("userId").toString());
		
		EPContactsDto contactsDto = new EPContactsDto();
		contactsDto.setContactsId(contactsId);
		contactsDto.setUpdateTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		contactsDto.setIsDeleted(1);
		contactsDto.setDeleteUserid(userId);
		contactsDto.setDeleteTime(Integer.valueOf((int)(System.currentTimeMillis() / 1000)));
		this.epContactsDao.updateEPContactsInfo(contactsDto);
		//this.epContactsDao.deleteRelationByContacts(contactsId);
		
		this.epCustomDao.updateEPCustomerByContactsId(contactsId);
		
		rslt.success("success");
		return rslt;
	}
	
	
	// 联系人信息end

}
