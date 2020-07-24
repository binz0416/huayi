package com.wicresoft.ghealth.custom.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.custom.service.ICustommanagerService;

@RestController
@RequestMapping(value = "/custommanager")
public class CustommanagerController {

	@Resource
	private ICustommanagerService custommanagerService;

	// 酒店系统和云健康系统对接接口
	@RequestMapping(value = "/hotel", method = RequestMethod.POST)
	public Response hotel(HttpServletRequest request, @RequestBody Map<String, Object> hotel) throws BusinessException {
		Response result = custommanagerService.hotel(hotel);
		return result;
	}

	// 客户信息主键搜索
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		// customByPK
		Response result = null;
		Utils.putUserInfoToList(request, list);
		if (StringUtils.equals("customAll", (String) list.get("apiflag"))) {
			result = custommanagerService.selectAll(list);
		}
		return result;
	}

}