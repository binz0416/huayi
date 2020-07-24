package com.wicresoft.ghealth.catering.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.catering.service.ISetMealService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.dto.UserInfo;

@RestController
@RequestMapping(value = "/setmeal")
public class SetMealController {

	@Resource
	private ISetMealService setMealService;

	@RequestMapping(value = "/camera", method = RequestMethod.POST)
	@Authorization
	public Response camera(HttpServletRequest request, @RequestBody Map<String, Object> camera) throws BusinessException {
		Response result = setMealService.camera(camera);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list)
			throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		list.put(CommonConst.Login_User, loginUser);
		Response rslt = null;

		if (StringUtils.equals("getPrintData", (String) list.get("apiflag"))) {
			rslt = this.setMealService.getPrintData(list);
		} else if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.setMealService.getCustomers(list);
		} else if (StringUtils.equals("getCustomerMeals", (String) list.get("apiflag"))) {
			rslt = this.setMealService.getCustomerMeals(list);
		} else {
			rslt = this.setMealService.refresh(list);
		}

		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody Map<String, Object> edit) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		edit.put(CommonConst.Login_User, loginUser);
		Response rslt = this.setMealService.isPrintOK(edit);
		return rslt;
	}
}
