package com.wicresoft.ghealth.sys.service;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.sys.dto.UserRecord;

public interface ISystemUsersService {

	// public Response list(Map<String, Object> list) throws BusinessException;
	public Response add(ApplicationContext act, UserRecord record) throws BusinessException;

	public Response edit(ApplicationContext act, UserRecord record) throws BusinessException;
	// public Response dele(Map<String, Object> dele);

	public Map<String, Object> userLogin(String userName, String passWd);

	public Response getUserAuthOptions(Map<String, Object> list);

	public Response getUsers(Map<String, Object> condition);

	// public String selectCount(Map<String, Object> list);

}
