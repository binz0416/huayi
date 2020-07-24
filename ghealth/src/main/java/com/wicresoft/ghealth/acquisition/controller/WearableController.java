package com.wicresoft.ghealth.acquisition.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wicresoft.ghealth.acquisition.service.IWearableService;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.Response;

@RestController
@RequestMapping(value = "/wearable")
//穿戴设备
public class WearableController {
	
	@Resource
	private IWearableService wearableService;
	
	@RequestMapping(value = "/heartbook", method = RequestMethod.POST)
	public Response heartbook() {
		Response rslt = new Response();
		rslt = wearableService.heartbook();
		return rslt;
	}

	@RequestMapping(value = "/additional", method = RequestMethod.POST)
	@Authorization
	public Response additional(HttpServletRequest request, @RequestBody Map<String, Object> additional) {
		Response rslt = new Response();
		rslt = wearableService.getHeartBySN(additional);
		return rslt;
	}

}
