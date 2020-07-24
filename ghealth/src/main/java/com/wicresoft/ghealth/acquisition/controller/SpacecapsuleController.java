package com.wicresoft.ghealth.acquisition.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wicresoft.ghealth.acquisition.service.ISpacecapsuleService;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.dto.UserInfo;

@RestController
@RequestMapping(value = "/spacecapsule")
// 太空舱
public class SpacecapsuleController {

	@Resource
	private ISpacecapsuleService spacecapsuleService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put(CommonConst.Login_User, loginUser);
		Response rslt = this.spacecapsuleService.list(list);
		return rslt;
	}

	@RequestMapping(value = "/additional", method = RequestMethod.POST)
	@Authorization
	public Response additional(HttpServletRequest request, @RequestBody Map<String, Object> add) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		add.put(CommonConst.Login_User, loginUser);
		Response rslt = this.spacecapsuleService.add(add);
		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody Map<String, Object> edit) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		edit.put(CommonConst.Login_User, loginUser);
		Response rslt = this.spacecapsuleService.edit(edit);
		return rslt;
	}

	@RequestMapping(value = "/dele", method = RequestMethod.POST)
	@Authorization
	public Response dele(HttpServletRequest request, @RequestBody Map<String, Object> dele) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		dele.put(CommonConst.Login_User, loginUser);
		Response rslt = this.spacecapsuleService.dele(dele);
		return rslt;
	}
}