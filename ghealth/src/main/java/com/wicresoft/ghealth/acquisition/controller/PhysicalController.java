package com.wicresoft.ghealth.acquisition.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.acquisition.dto.PhysicalDto;
import com.wicresoft.ghealth.acquisition.service.IPhysicalService;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.UserInfo;

@RestController
@RequestMapping(value = "/physicalreport")
public class PhysicalController {

	@Resource
	private IPhysicalService physicalService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		Utils.putUserInfoToList(request, list);
		Response rslt = new Response();
		if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.physicalService.getCustomers(list);
		} else if (StringUtils.equals("getReport", (String) list.get("apiflag"))) {
			rslt = this.physicalService.list(list);
		}
		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid PhysicalDto physicalDto, BindingResult result)
			throws BusinessException {
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			rslt = this.physicalService.edit(physicalDto);
		}
		return rslt;
	}

	@RequestMapping(value = "/dele", method = RequestMethod.POST)
	@Authorization
	public Response dele(HttpServletRequest request, @RequestBody Map<String, Object> dele) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		dele.put(CommonConst.Login_User, loginUser);
		Response rslt = this.physicalService.dele(dele);
		return rslt;
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@Authorization
	public Response upload(HttpSession session, HttpServletRequest request,
			@RequestParam(CommonConst.KEY_UPLOAD_FILE) CommonsMultipartFile uploadfile)
			throws Exception {
		Map<String, Object> upload = new HashMap<String, Object>();
		upload.put(CommonConst.KEY_UPLOAD_FILE, uploadfile);
		Response rslt = Utils.reportUpload(upload);
		return rslt;
	}
	 

}
