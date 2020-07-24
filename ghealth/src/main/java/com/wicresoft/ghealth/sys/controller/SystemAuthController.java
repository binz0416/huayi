package com.wicresoft.ghealth.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.sys.dto.Role;
import com.wicresoft.ghealth.sys.service.ISystemAuthService;

@RestController
@RequestMapping(value = "/auth")
// 权限配置
public class SystemAuthController {

	@Resource
	private ISystemAuthService systemAuthService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put(CommonConst.Login_User, loginUser);
		Response rslt = null;
		if (StringUtils.equals("init", (String) list.get("apiflag"))) {
			rslt = this.systemAuthService.init(list);
		} else {
			rslt = this.systemAuthService.list(list);
		}

		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid Role role, BindingResult result) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(this.formatErrors(act, result));
		} else {
			if (StringUtils.equals("editRole", (String) role.getApiflg())) {
				rslt = this.systemAuthService.editRole(act, role);
			}else {
				rslt = this.systemAuthService.editRoleMenu(act, role);
			}
			
		}
		return rslt;
	}

	@RequestMapping(value = "/additional", method = RequestMethod.POST)
	@Authorization
	public Response additional(HttpServletRequest request, @RequestBody @Valid Role rolemenu, BindingResult result) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(this.formatErrors(act, result));
		} else {
			
			rslt = this.systemAuthService.addRole(act, rolemenu);
		}
		return rslt;

	}
//
//	@RequestMapping(value = "/dele", method = RequestMethod.POST)
//	@Authorization
//	public Response dele(HttpServletRequest request, @RequestBody Map<String, Object> dele) throws BusinessException {
//		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
//		dele.put(CommonConst.Login_User, loginUser);
//
//		Response rslt = this.systemUsergroupsService.dele(dele);
//		return rslt;
//	}
	
	private Map<String, Object> formatErrors(ApplicationContext act, BindingResult result) {
		List<FieldError> errors = result.getFieldErrors();
		Map<String, Object> errMap = new HashMap<String, Object>();
		for (FieldError error : errors) {
			errMap.put(error.getField(), act.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
		}
		return errMap;
	}
}
