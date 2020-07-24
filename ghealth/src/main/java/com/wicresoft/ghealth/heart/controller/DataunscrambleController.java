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
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dto.ArchivesDetailDto;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.heart.service.IDataunscrambleService;

@RestController
@RequestMapping(value = "/dataunscramble")
// 数据解读
public class DataunscrambleController {

	@Resource
	private IDataunscrambleService dataunscrambleService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Utils.putUserInfoToList(request, list);
		String path = request.getSession().getServletContext().getRealPath(CommonConst.ECG_URL);
		list.put("path", path);
		if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.dataunscrambleService.getCustomers(list);
		} else if (StringUtils.equals("getReport", (String) list.get("apiflag"))) {
			rslt = this.dataunscrambleService.init(list);
		} else if (StringUtils.equals("getEcgData", (String) list.get("apiflag"))) {
			rslt = this.dataunscrambleService.getEcgData(list);
		}
		return rslt;
	}

	@RequestMapping(value = "/additional", method = RequestMethod.POST)
	@Authorization
	public Response additional(HttpServletRequest request, @RequestBody Map<String, Object> add)
			throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		add.put(CommonConst.Login_User, loginUser);
		Response rslt = this.dataunscrambleService.add(add);
		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response edit(HttpServletRequest request, @RequestBody @Valid ArchivesDetailDto detailDto, BindingResult result) throws BusinessException {
		
		Response rslt = new Response();
		if (result.hasErrors()) {
			rslt.failure(Utils.formatErrors(result));
		} else {
			rslt = this.dataunscrambleService.edit(detailDto);
		}
		return rslt;
	}

	@RequestMapping(value = "/dele", method = RequestMethod.POST)
	@Authorization
	public Response dele(HttpServletRequest request, @RequestBody Map<String, Object> dele) throws BusinessException {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		dele.put(CommonConst.Login_User, loginUser);
		Response rslt = this.dataunscrambleService.dele(dele);
		return rslt;
	}
}
