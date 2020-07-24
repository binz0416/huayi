package com.wicresoft.ghealth.authorization.interceptor;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.wicresoft.ghealth.authorization.annotation.Authorization;
import com.wicresoft.ghealth.authorization.jwt.Jwt;
import com.wicresoft.ghealth.authorization.manager.TokenManager;
import com.wicresoft.ghealth.authorization.pojo.UserDetails;
import com.wicresoft.ghealth.auto.mapper.TActlogMapper;
import com.wicresoft.ghealth.auto.pojo.TActlog;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.sys.dao.SystemUsersDao;

/**
 * 自定义拦截器，对请求进行身份验证
 * 
 * @author binz
 * @date 2017/07/07
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private SystemUsersDao sysDao;
	
	@Resource
	private CommonDao commonDao;

	@Resource
	private TActlogMapper actLogDao;

	// 管理身份验证操作的对象
	private TokenManager manager;

	// 存放鉴权信息的Header名称，默认是Authorization
	private String httpHeaderName = "Authorization";

	// 鉴权信息的无用前缀，默认为空
	private String httpHeaderPrefix = "";

	// 鉴权失败后返回的错误信息，默认为401 unauthorized
	@SuppressWarnings("unused")
	private String unauthorizedErrorMessage = "401 unauthorized";

	// 鉴权失败后返回的HTTP错误码，默认为401
	private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

	public void setManager(TokenManager manager) {
		this.manager = manager;
	}

	public void setHttpHeaderName(String httpHeaderName) {
		this.httpHeaderName = httpHeaderName;
	}

	public void setHttpHeaderPrefix(String httpHeaderPrefix) {
		this.httpHeaderPrefix = httpHeaderPrefix;
	}

	public void setUnauthorizedErrorMessage(String unauthorizedErrorMessage) {
		this.unauthorizedErrorMessage = unauthorizedErrorMessage;
	}

	public void setUnauthorizedErrorCode(int unauthorizedErrorCode) {
		this.unauthorizedErrorCode = unauthorizedErrorCode;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			ApplicationContext act = ContextLoader.getCurrentWebApplicationContext();
			// 如果不是映射到方法直接通过
			TActlog actLog = new TActlog();
			if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
				boolean check = false;
				String errormsg = act.getMessage("E0001", null, Locale.getDefault());
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				Method method = handlerMethod.getMethod();
				// 是否需要权鉴 - 是
				if (method.getAnnotation(Authorization.class) != null
						|| handlerMethod.getBeanType().getAnnotation(Authorization.class) != null) {
					// 从header中得到token
					String token = "";
					String loginUser = "";

					token = request.getHeader(httpHeaderName);
					if (StringUtils.isBlank(token)) {
						token = request.getParameter(httpHeaderName);
					}
					loginUser = request.getHeader(CommonConst.Login_User);
					if (StringUtils.isBlank(loginUser)) {
						loginUser = request.getParameter(CommonConst.Login_User);
					}

					String url = request.getRequestURI();
					String contextPath = request.getContextPath();
					url = url.replaceFirst(contextPath, "");

					actLog.setUrl(url);
					actLog.setUsername(loginUser);
					actLog.setAccesstime(Utils.dateTimeToStr());

					if (token != null && token.startsWith(httpHeaderPrefix) && token.length() > 0) {
						token = token.substring(httpHeaderPrefix.length());
						// 验证token
						String secret = manager.getSecret(loginUser);
						UserDetails ud = Jwt.unsign(token, UserDetails.class, secret);
						if (ud != null && ud.getUsername().equals(loginUser)) {
							
							Map<String, Object> param = new HashMap<String, Object>();
							param.put("username", loginUser);
							UserInfo userInfo = this.commonDao.getUserInfo(param);
							if (userInfo != null) {

								String groupid = userInfo.getGroupId();

								String menu = "/" + url.substring(1, url.lastIndexOf('/'));
								String action = url.substring(url.lastIndexOf('/') + 1, url.length());

								Map<String, Object> para = new HashMap<String, Object>();
								para.put("roleids", groupid.split(","));
								para.put("url", menu);
								para.put("paction", action);
								int count = this.sysDao.getPermission(para);
								if (count > 0) {
									request.setAttribute(CommonConst.REQUEST_CURRENT_KEY, userInfo);
									check = true;
								} else {
									errormsg = act.getMessage("E0002", null, Locale.getDefault());
								}
							}
						} else {
							errormsg = act.getMessage("E0012", null, Locale.getDefault());
						}
					}  else {
						
						// 白名单
						String ip = request.getHeader("x-forwarded-for");
						if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
							ip = request.getHeader("Proxy-Client-IP");
						}
						if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
							ip = request.getHeader("WL-Proxy-Client-IP");
						}
						if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
							ip = request.getRemoteAddr();
						}

						check = true;

					}
					
					// token验证失败
					if (check) {
						actLog.setStat("N");
						commonDao.insertAndGetId(actLog);
						request.setAttribute(CommonConst.CST_LOG_ID, actLog.getActlogId());
						return true;
					} else {
						request.setAttribute(CommonConst.REQUEST_CURRENT_KEY, null);
						response.setStatus(unauthorizedErrorCode);
						response.setContentType(MediaType.APPLICATION_JSON_VALUE);
						BufferedWriter writer = new BufferedWriter(
								new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
						writer.write(errormsg);
						writer.close();
						actLog.setStat("E");
						actLog.setInfo(errormsg + "token验证失败:" + token + "||" + loginUser);
						actLogDao.insert(actLog);
						return false;

					}
				}
			}
			// 为了防止以恶意操作直接在REQUEST_CURRENT_KEY写入key，将其设为null
			request.setAttribute(CommonConst.REQUEST_CURRENT_KEY, null);
			request.setAttribute(CommonConst.CST_LOG_ID, null);
			return true;
		} catch (Exception e) {
			request.setAttribute(CommonConst.REQUEST_CURRENT_KEY, null);
			response.setStatus(unauthorizedErrorCode);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			return false;
		}
	}
}
