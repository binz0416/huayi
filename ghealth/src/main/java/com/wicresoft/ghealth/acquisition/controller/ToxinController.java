package com.wicresoft.ghealth.acquisition.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.acquisition.dto.Toxin;
import com.wicresoft.ghealth.acquisition.service.IToxinService;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;

@RestController
@RequestMapping(value = "/toxindetection")
@MultipartConfig
public class ToxinController {

	@Resource
	private IToxinService toxinService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@Authorization
	public Response list(HttpServletRequest request, @RequestBody Map<String, Object> list) throws BusinessException {
		Response rslt = new Response();
		Utils.putUserInfoToList(request, list);
		if (StringUtils.equals("getCustomers", (String) list.get("apiflag"))) {
			rslt = this.toxinService.getCustomers(list);
		} else {
			rslt = this.toxinService.list(list);
		}
		return rslt;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Authorization
	public Response save(HttpServletRequest request, @RequestBody @Valid Toxin record, BindingResult result)
			throws BusinessException {
		// UserInfo loginUser = (UserInfo)
		// request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		Response res = new Response();
		if (result.hasErrors()) {
			res.failure(this.formatErrors(result));
			System.out.println("error");
		} else {
			res = this.toxinService.save(record);
		}
		// Response rslt = this.toxinService.add(add);
		return res;
	}

	/*
	 * @RequestMapping(value = "/upload", method = RequestMethod.POST)
	 * 
	 * @Authorization public Response upload(HttpSession session,
	 * HttpServletRequest request,
	 * 
	 * @RequestParam(CommonConst.KEY_UPLOAD_FILE) CommonsMultipartFile
	 * uploadfile, @RequestParam String apiflag) throws Exception { Map<String,
	 * Object> upload = new HashMap<String, Object>();
	 * upload.put(CommonConst.KEY_UPLOAD_FILE, uploadfile); Response rslt =
	 * Utils.upload(upload); return rslt; }
	 */

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@Authorization
	protected Map<String, Object> upload(HttpServletRequest request) throws BusinessException {
		// 打开正式文件下载路径
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/fileDownloadPath/";
		// 打开测试文件下载路径
//		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/fileDownloadPathTest/";
		// 下载文件路径，会调用toxindetection/download方法
//		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/toxindetection/download?fileName=";
		
		// 转化request
		DefaultMultipartHttpServletRequest mulReq = (DefaultMultipartHttpServletRequest) request;
		// 获取上传表单的name值
		// String picName = request.getParameter("file");
		// 解析为多部件文件
		CommonsMultipartFile mfile = (CommonsMultipartFile) mulReq.getFile("file");

		Map<String, Object> upload = new HashMap<String, Object>();
		upload.put(CommonConst.KEY_UPLOAD_FILE, mfile);
		upload.put("url", url);
		Map<String, Object> rslt = Utils.upload(upload);

		return rslt;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@Authorization
	public ResponseEntity<byte[]> download(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "fileName") String fileName) throws BusinessException {
		ResponseEntity<byte[]> rslt = Utils.download(fileName);
		return rslt;
	}

	private List<Map<String, Object>> formatErrors(BindingResult result) {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		List<FieldError> errors = result.getFieldErrors();
		List<Map<String, Object>> errList = new ArrayList<Map<String, Object>>();
		boolean added = false;
		for (FieldError error : errors) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			if ("customerId".equals(error.getField()) || "checkintimeStr".equals(error.getField())) {
				if (!added) {
					errMap.put("error", act.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
					errList.add(errMap);
					added = true;
				}
			}
			if ("reportArea".equals(error.getField())) {
				errMap.put("error", act.getMessage(error.getDefaultMessage(), null, Locale.getDefault()));
				errList.add(errMap);
			}
		}
		return errList;
	}

}
