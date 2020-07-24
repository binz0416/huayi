/**
 * 
 */
package com.wicresoft.ghealth.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.sys.dao.SystemAuthDao;
import com.wicresoft.ghealth.sys.dto.Role;
import com.wicresoft.ghealth.sys.dto.RoleMenu;
import com.wicresoft.ghealth.sys.service.ISystemAuthService;

@Service("systemAuthService")
public class SystemAuthService implements ISystemAuthService {

	@Resource
	private SystemAuthDao systemAuthDao;

	@Resource
	private CommonDao commonDao;

	@Override
	public Response init(Map<String, Object> list) {
		Response rslt = new Response();
		Map<String, Object> rmap = new HashMap<String, Object>();
		List<Map<String, Object>> roles = systemAuthDao.getRols(list);
		if (!roles.isEmpty()) {
			rmap.put("roles", roles);
		}
		List<Map<String, Object>> actions = commonDao.getActions();
		if (!actions.isEmpty()) {
			rmap.put("actions", actions);
		}
		if (list.get("roleId").toString() != null && !list.get("roleId").toString().isEmpty()) {
			List<Map<String, Object>> data = systemAuthDao.getRoleMenu(new Long(list.get("roleId").toString()));
			
			if (!data.isEmpty()) {
				List<Map<String, Object>> roleMenus = new ArrayList<Map<String, Object>>();
				Map<String, Object> parentMenu = new HashMap<String, Object>();
				List<Map<String, Object>> childMenus = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < data.size(); i++) {
					Map<String, Object> nowRecord = data.get(i);
					// 父菜单的情况
					if ("T".equals(nowRecord.get("expand").toString())) {
						parentMenu.put("menuid", nowRecord.get("menu_id").toString());
						parentMenu.put("title", nowRecord.get("name").toString());
						parentMenu.put("expand", true);
						if ("0".equals(nowRecord.get("deleted").toString())) {
							parentMenu.put("checked", true);
						} else {
							parentMenu.put("checked", false);
						}
					} else {
						Map<String, Object> childMenu = new HashMap<String, Object>();
						childMenu.put("menuid", nowRecord.get("menu_id").toString());
						childMenu.put("title", nowRecord.get("name").toString());
						childMenu.put("expand", false);
						if ("0".equals(nowRecord.get("deleted").toString())) {
							childMenu.put("checked", true);
						} else {
							childMenu.put("checked", false);
						}
						if (!StringUtils.isEmpty(nowRecord.get("paction"))) {
							childMenu.put("actions", nowRecord.get("paction"));
						} else {
							childMenu.put("actions", "");
						}
						childMenus.add(childMenu);
					}
					// Last Record
					if (i == data.size() - 1) {
						parentMenu.put("children", childMenus);
						roleMenus.add(parentMenu);
					} else {
						Map<String, Object> nexRecord = data.get(i + 1);
						if ("T".equals(nexRecord.get("expand").toString())) {
							parentMenu.put("children", childMenus);
							roleMenus.add(parentMenu);
							parentMenu = new HashMap<String, Object>();
							childMenus = new ArrayList<Map<String, Object>>();
						}
					}
				}
				rmap.put("roleMenus", roleMenus);
			}
			
		}
		rmap.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_AUTHCOLUMNS));
		rslt.success(rmap);
		
		return rslt;
	}

	public Response list(Map<String, Object> list) {
		Response rslt = new Response();
		// try {
		// SystemAuthModelList authModelList = new SystemAuthModelList();
		// SystemAuthModelFunctionList funcList = new
		// SystemAuthModelFunctionList();
		//
		// List<LinkedHashMap<String, String>> actionList = new
		// ArrayList<LinkedHashMap<String, String>>();
		// List<LinkedHashMap<String, String>> authList = new
		// ArrayList<LinkedHashMap<String, String>>();
		// List<SystemAuthModelFunctionList> funcLists = new
		// ArrayList<SystemAuthModelFunctionList>();
		//
		// List<Map<String, Object>> info = this.authinfoDao
		// .selectAuthorityFuncListByGroupId(list.get("groupid").toString());
		//
		// LinkedHashMap<String, String> actionMap = new LinkedHashMap<String,
		// String>();
		// LinkedHashMap<String, String> authMap = new LinkedHashMap<String,
		// String>();
		//
		// if (info != null && !info.isEmpty()) {
		// for (int i = 0; i < info.size(); i++) {
		//
		// actionList = new ArrayList<LinkedHashMap<String, String>>();
		// authList = new ArrayList<LinkedHashMap<String, String>>();
		// funcList = new SystemAuthModelFunctionList();
		// actionMap = new LinkedHashMap<String, String>();
		// authMap = new LinkedHashMap<String, String>();
		//
		// funcList.setMenuid(info.get(i).get("menuid").toString());
		// funcList.setName(info.get(i).get("name").toString());
		// funcList.setParentid(info.get(i).get("parentid").toString());
		//
		// if (!info.get(i).get("list").toString().isEmpty()) {
		// actionMap.put("list", info.get(i).get("listm").toString());
		// authMap.put("list", info.get(i).get("list").toString());
		// }
		//
		// if (!info.get(i).get("additional").toString().isEmpty()) {
		// actionMap.put("additional",
		// info.get(i).get("additionalm").toString());
		// authMap.put("additional", info.get(i).get("additional").toString());
		// }
		//
		// if (!info.get(i).get("edit").toString().isEmpty()) {
		// actionMap.put("edit", info.get(i).get("editm").toString());
		// authMap.put("edit", info.get(i).get("edit").toString());
		// }
		//
		// if (!info.get(i).get("dele").toString().isEmpty()) {
		// actionMap.put("dele", info.get(i).get("delem").toString());
		// authMap.put("dele", info.get(i).get("dele").toString());
		// }
		//
		// actionList.add(actionMap);
		// authList.add(authMap);
		//
		// funcList.setAction(actionList);
		// funcList.setAuth(authList);
		//
		// funcLists.add(funcList);
		// }
		//
		// authModelList.setFunctions(funcLists);
		// authModelList.setGroupid(info.get(0).get("groupid").toString());
		// authModelList.setGroupname(info.get(0).get("groupname").toString());
		// authModelList.setGroupshortname(info.get(0).get("groupshortname").toString());
		//
		// }
		//
		// rslt.success(authModelList);
		// } catch (Exception e) {
		// rslt.failure(e.getMessage());
		// }
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response editRole(ApplicationContext act, Role role) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> roleMap = new HashMap<String, Object>();
		roleMap.put("roleId", role.getRoleId());
		roleMap.put("roleName", role.getRoleName());
		int count = this.systemAuthDao.checkHasRoleForUpdate(roleMap);

		if (count > 0) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("role.hasrole", null, Locale.getDefault()));
			rslt.failure(errMap);
		} else {

			// 系统时间
			Date sysTime = new Date();
			role.setUpdateDateTime(sysTime);
			this.systemAuthDao.updateRole(role);

			rslt.success();
		}

		return rslt;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response editRoleMenu(ApplicationContext act, Role role) throws BusinessException {
		Response rslt = new Response();

		// 系统时间
		Date sysTime = new Date();
		role.setUpdateDateTime(sysTime);
		this.systemAuthDao.updateRole(role);

		this.systemAuthDao.deleteRoleMenu(role.getRoleId());

		Long roleId = role.getRoleId();
		// 添加菜单权限
		for (RoleMenu rolemenu : role.getRoleMenus()) {
			rolemenu.setRoleId(roleId);
			rolemenu.setCreateDateTime(sysTime);
			rolemenu.setUpdateDateTime(sysTime);
			
			if (rolemenu.isChecked()) {
				rolemenu.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
			} else {
				rolemenu.setDeleted(CommonConst.DEFAULT_DELETE_FLG_1);
			}
			rolemenu.setActions(null);
			this.systemAuthDao.insertRoleMenu(rolemenu);
			
			for (RoleMenu children : rolemenu.getChildren()) {
				children.setRoleId(roleId);
				children.setCreateDateTime(sysTime);
				children.setUpdateDateTime(sysTime);
				
				if (children.isChecked()) {
					children.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
				} else {
					children.setDeleted(CommonConst.DEFAULT_DELETE_FLG_1);
					children.setActions(null);
				}
				this.systemAuthDao.insertRoleMenu(children);
			}
		}
		rslt.success();

		return rslt;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response addRole(ApplicationContext act, Role role) throws BusinessException {
		Response rslt = new Response();

		int count = this.systemAuthDao.checkHasRole(role.getRoleName());

		if (count > 0) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("role.hasrole", null, Locale.getDefault()));
			rslt.failure(errMap);
		} else {

			// 系统时间
			Date sysTime = new Date();
			role.setCreateDateTime(sysTime);
			role.setUpdateDateTime(sysTime);
			role.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
			this.systemAuthDao.insertRole(role);

			// 添加菜单权限
			Long roleId = role.getRoleId();
			List<Map<String, Object>> menuItems = this.systemAuthDao.getMenuItems();
			RoleMenu roleMenu = new RoleMenu();
			for (int i = 0; i < menuItems.size(); i++) {
				roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuid(menuItems.get(i).get("menu_id").toString());
				roleMenu.setCreateDateTime(sysTime);
				roleMenu.setUpdateDateTime(sysTime);
				roleMenu.setActions(null);
				roleMenu.setDeleted(CommonConst.DEFAULT_DELETE_FLG_1);
				
				this.systemAuthDao.insertRoleMenu(roleMenu);
			}
			
			rslt.success();
		}

		return rslt;
	}

}
