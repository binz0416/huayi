package com.wicresoft.ghealth.sys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.sys.dto.UserRecord;
import com.wicresoft.ghealth.sys.service.ISystemUsersService;

@RestController
@RequestMapping(value = "/users")
// 用户管理
public class SystemUsersController {

	@Resource
	private ISystemUsersService systemUsersService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put(CommonConst.Login_User, loginUser);
		Response rslt = new Response();
		if (StringUtils.equals("selectUsers", (String) list.get("apiflag"))) {
			rslt = this.systemUsersService.getUsers(list);
			return rslt;
		} else if (StringUtils.equals("userAuthOptions", (String) list.get("apiflag"))) {
			rslt = this.systemUsersService.getUserAuthOptions(list);
			return rslt;
		} else {
			return new Response();
		}
	}

	@RequestMapping(value = "/additional", method = RequestMethod.POST)
	@Authorization
	public Response additional(HttpServletRequest request, @RequestBody @Valid UserRecord record, BindingResult result)
			throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		// User Set
		record.setUpdateuser(loginUser.getUsername());
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			rslt = this.systemUsersService.add(act, record);
		}
		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid UserRecord record, BindingResult result)
			throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		// User Set
		record.setUpdateuser(loginUser.getUsername());
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			rslt = this.systemUsersService.edit(act, record);
		}
		return rslt;
	}
}
