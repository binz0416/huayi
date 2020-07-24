package com.wicresoft.ghealth.sys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.sys.dto.Config;
import com.wicresoft.ghealth.sys.service.ISystemConfigService;

@RestController
@RequestMapping(value = "/config")
// 系统配置
public class SystemConfigController {

	@Resource
	private ISystemConfigService systemConfigService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();	
		rslt = this.systemConfigService.getConfig();
		return rslt;
	}


	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid Config record, BindingResult result)
			throws BusinessException {
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			if(record.getConfigId()==null){
				rslt = this.systemConfigService.add(record);
			}else{
				rslt = this.systemConfigService.edit(record);
			}
			
		}
		return rslt;
	}
}
