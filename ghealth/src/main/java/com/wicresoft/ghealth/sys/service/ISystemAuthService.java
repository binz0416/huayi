package com.wicresoft.ghealth.sys.service;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.sys.dto.Role;

public interface ISystemAuthService {
	
	public Response init(Map<String, Object> list);
	
	public Response list(Map<String, Object> list);
	
	public Response addRole(ApplicationContext act, Role role) throws BusinessException;

	public Response editRole(ApplicationContext act, Role role) throws BusinessException;
	
	public Response editRoleMenu(ApplicationContext act, Role role) throws BusinessException;
	
}
