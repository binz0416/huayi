package com.wicresoft.ghealth.sys.controller;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import com.wicresoft.ghealth.authorization.jwt.Jwt;
import com.wicresoft.ghealth.authorization.manager.TokenManager;
import com.wicresoft.ghealth.authorization.pojo.UserDetails;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.sys.service.ISystemUsersService;

@RestController
public class LoginController {

	@Autowired
	private TokenManager tokenManager;

	@Resource
	private ISystemUsersService systemUsersService;

	@RequestMapping(value = "/actlogin", method = RequestMethod.POST)
	public Response login(HttpServletRequest request,@RequestBody Map<String, Object> data) throws BusinessException {
		ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
		Response rslt = new Response();

		String userName = (String)data.get("userName");
		String passWd = (String)data.get("passWd");
		
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(passWd)) {
			Map<String, Object> ret = this.systemUsersService.userLogin(userName, passWd);
			if (ret != null) {
				String secret = UUID.randomUUID().toString().replace("-", "#*");
				tokenManager.createRelationship(userName, secret);
				UserDetails ud = new UserDetails();
				ud.setUsername(userName);
				// 给用户jwt加密生成token
				String token = Jwt.sign(ud, 60L * 1000L * 30L, secret);
				ret.put("token", token);

				rslt.success(ret);
			} else {
				throw new BusinessException(act.getMessage("E0003", null, Locale.getDefault()));
			}
		} else {
			throw new BusinessException(act.getMessage("E0004", null, Locale.getDefault()));
		}
		
		return rslt;
	}
	
	@RequestMapping(value = "/actlogout", method = RequestMethod.POST)
	public Response logout(HttpServletRequest request) {
		Response rslt = new Response();

		String userName = (String) request.getParameter("userName");
		tokenManager.delRelationshipByKey(userName);

		rslt.success();
		return rslt;
	}
}