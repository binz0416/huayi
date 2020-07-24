package com.wicresoft.ghealth.heart.controller;

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
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.SetMealInfo;
import com.wicresoft.ghealth.heart.service.IProjectmanagerService;

@RestController
@RequestMapping(value = "/projectmanager")
// 健康方案
public class ProjectmanagerController {

	@Resource
	private IProjectmanagerService projectmanagerService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) 
			throws BusinessException {
		Utils.putUserInfoToList(request, list);
		Response rslt = new Response();
		if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.projectmanagerService.getCustomers(list);
		} else if (StringUtils.equals("getMealInfos", (String) list.get("apiflag"))) {
			rslt = this.projectmanagerService.getCustomerMealInfos(list);
		} 
		return rslt;
	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response save(HttpServletRequest request, @RequestBody @Valid SetMealInfo mealInfo , BindingResult result) throws BusinessException {
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			rslt = this.projectmanagerService.save(mealInfo);
		}
		return rslt;
	}

}
