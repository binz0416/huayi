package com.wicresoft.ghealth.authorization.controller;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.authorization.jwt.Jwt;
import com.wicresoft.ghealth.authorization.manager.TokenManager;
import com.wicresoft.ghealth.authorization.pojo.UserDetails;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.dto.UserInfo;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * 
 * @author binz
 * @date 2017/07/07.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

	@Autowired
	private TokenManager tokenManager;

	@RequestMapping(method = RequestMethod.POST)
	public Response createToken(@RequestParam String username) {
		// 生成一个token
		String secret = UUID.randomUUID().toString().replace("-", "#*");
		tokenManager.createRelationship(username, secret);
		UserDetails ud = new UserDetails();
		ud.setUsername(username);
		// 给用户jwt加密生成token
		String token = Jwt.sign(ud, 60L * 1000L * 30L, secret);
		return new Response().success(token);
	}

	@RequestMapping(method = RequestMethod.GET)
	@Authorization
	public Response getUsername(HttpServletRequest request) {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		// 生成一个token
		return new Response().success(loginUser.getUsername());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@Authorization
	public Response logout(HttpServletRequest request) {
		UserInfo loginUser = (UserInfo) request.getAttribute(CommonConst.REQUEST_CURRENT_KEY);
		tokenManager.delRelationshipByKey(loginUser.getUsername());
		return new Response().success();
	}

}
