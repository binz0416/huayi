package com.wicresoft.ghealth.sys.dao;

import java.util.List;
import java.util.Map;

import com.wicresoft.ghealth.sys.dto.Role;
import com.wicresoft.ghealth.sys.dto.RoleMenu;

public interface SystemAuthDao {
	
	List<Map<String, Object>> getRols(Map<String, Object> param);
	
	List<Map<String, Object>> getRoleMenu(Long param);
	
	int checkHasRole(String roleName);
	
	int checkHasRoleForUpdate(Map<String, Object> param);
	
	void insertRole(Role role);
	
	void updateRole(Role role);
	
	List<Map<String, Object>> getMenuItems();
	
	void deleteRoleMenu(Long roleId);
	
	void insertRoleMenu(RoleMenu roleMenu);
}