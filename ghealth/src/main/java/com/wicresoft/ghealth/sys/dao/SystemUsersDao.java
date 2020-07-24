package com.wicresoft.ghealth.sys.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.sys.dto.UserRecord;

public interface SystemUsersDao {
	
	int getPermission(Map<String, Object> condition);
	
	List<Map<String, Object>> selectRoleMenuByGroupId(String[] groupids);
	
	List<Map<String, Object>> getUsers(Map<String, Object> param);
	
	int getUsersCount(Map<String, Object> param);
	
	int checkHasUser(String username);
	
	void insertUser(UserRecord record);
	
	int checkHasUserForUpd(String userid);
	
	void updateUser(UserRecord record);
	
	void deleUserRoles(String userid);
	
}