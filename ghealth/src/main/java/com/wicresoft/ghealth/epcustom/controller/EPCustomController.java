package com.wicresoft.ghealth.epcustom.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.epcustom.dto.EPContactsDto;
import com.wicresoft.ghealth.epcustom.dto.EPCustomerDto;
import com.wicresoft.ghealth.epcustom.dto.EPCommonDto;
import com.wicresoft.ghealth.epcustom.service.IEPCustomService;

@RestController
@RequestMapping(value = "/epcustommanager")
//企业客户管理
public class EPCustomController {

	@Resource
	private IEPCustomService epCustomService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put("ownerUserid", loginUser.getUserid());
		Response rslt = new Response();
		if (StringUtils.equals("getepCustomAll", (String) list.get("apiflag"))) {
			rslt = this.epCustomService.selectAllEPCustoms(list);
			return rslt;
		} else if (StringUtils.equals("getEPContacts", (String) list.get("apiflag"))) {
			rslt = this.epCustomService.selectAllEPContacts(list);
			return rslt;
		} else if (StringUtils.equals("getCustomTJ", (String) list.get("apiflag"))) {
			rslt = this.epCustomService.getCustomTjInfo(list);
			return rslt;
		} else {
			return new Response();
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid EPCommonDto epCommonDto,BindingResult result) 
			throws BusinessException {
		Response rslt = new Response();
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		
		if (result.hasErrors()) {
			rslt.failure(Utils.formatEPCustomErrors(result));
		} else {
			if (StringUtils.equals("custom", epCommonDto.getApiflag())) {
				EPCustomerDto epCustomerDto = epCommonDto.getEpCustomerDto();
				epCustomerDto.setCreatorUserid(Long.valueOf(loginUser.getUserid()));
				rslt = this.epCustomService.editEPCustomerInfo(epCustomerDto);
			}else if (StringUtils.equals("contacts", epCommonDto.getApiflag())) {
				EPContactsDto epContactsDto = epCommonDto.getEpContactsDto();
				epContactsDto.setCreatorUserid(Long.valueOf(loginUser.getUserid()));
				rslt = this.epCustomService.editEPContactsInfo(epContactsDto);
			}else if (StringUtils.equals("setFirst", epCommonDto.getApiflag())) {
				EPCustomerDto epCustomerDto = epCommonDto.getEpCustomerDto();
				epCustomerDto.setCreatorUserid(Long.valueOf(loginUser.getUserid()));
				rslt = this.epCustomService.editEPCustomerInfo(epCustomerDto);
			}
		}
		return rslt;
	}
	
	
	@RequestMapping(value = "/dele", method = RequestMethod.POST)
	@Authorization
	public Response dele(HttpServletRequest request, @RequestBody Map<String, Object> list, BindingResult result) 
			throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put("userId", loginUser.getUserid());
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			if (StringUtils.equals("custom", (String) list.get("apiflag"))) {
				rslt = this.epCustomService.deleEPCustomerInfo(list);
			}else if (StringUtils.equals("contacts", (String) list.get("apiflag"))) {
				rslt = this.epCustomService.deleEPContactsInfo(list);
			}
		}
		
		return rslt;
	}
}
