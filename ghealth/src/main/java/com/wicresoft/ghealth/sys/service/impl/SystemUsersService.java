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

import com.wicresoft.ghealth.auto.mapper.TActlogMapper;
import com.wicresoft.ghealth.auto.mapper.TUserRoleMapper;
import com.wicresoft.ghealth.auto.pojo.TActlog;
import com.wicresoft.ghealth.auto.pojo.TUserRole;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.CommonConst;
import com.wicresoft.ghealth.common.PaginationUtils;
import com.wicresoft.ghealth.common.Response;
import com.wicresoft.ghealth.common.Utils;
import com.wicresoft.ghealth.common.dao.CommonDao;
import com.wicresoft.ghealth.common.dto.UserInfo;
import com.wicresoft.ghealth.sys.dao.SystemUsersDao;
import com.wicresoft.ghealth.sys.dto.UserRecord;
import com.wicresoft.ghealth.sys.service.ISystemUsersService;

@Service("systemUsersService")
public class SystemUsersService implements ISystemUsersService {

	@Resource
	private SystemUsersDao sysUsersDao;

	@Resource
	private CommonDao commonDao;

	@Resource
	private TActlogMapper actLogDao;

	@Resource
	private TUserRoleMapper userRoleDao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response add(ApplicationContext act, UserRecord user) throws BusinessException {
		Response rslt = new Response();
		int count = this.sysUsersDao.checkHasUser(user.getUsername());
		if (count > 0) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("user.hasuser", null, Locale.getDefault()));
			rslt.failure(errMap);
		} else {
			// 系统时间
			Date sysTime = new Date();
			user.setCreatetime(sysTime);
			user.setUpdatetime(sysTime);
			// 追加用户
			user.setPassword(Utils.getMD5(user.getPassword()));
			sysUsersDao.insertUser(user);
			// 追加用户权限
			String userid = user.getUserid().toString();
			List<TUserRole> userRoles = user.getUserRoles();
			if (userRoles == null || userRoles.isEmpty()) {
				TUserRole userRole = new TUserRole();
				userRole.setUserId(userid);
				userRole.setRoleId(CommonConst.DEFAULT_DELETE_ROLE_ID);
				userRole.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
				userRole.setCreateDateTime(sysTime);
				userRole.setUpdateDateTime(sysTime);
				userRoleDao.insertSelective(userRole);
			} else {
				for (TUserRole userRole : userRoles) {
					userRole.setUserId(userid);
					userRole.setCreateDateTime(sysTime);
					userRole.setUpdateDateTime(sysTime);
					userRole.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
					userRoleDao.insertSelective(userRole);
				}
			}
			rslt.success();
		}
		return rslt;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Response edit(ApplicationContext act, UserRecord user) throws BusinessException {
		Response rslt = new Response();
		int count = this.sysUsersDao.checkHasUserForUpd(user.getUserid().toString());
		if (count < 1) {
			Map<String, Object> errMap = new HashMap<String, Object>();
			errMap.put("logic", act.getMessage("user.nouser", null, Locale.getDefault()));
			rslt.failure(errMap);
		} else {
			// 系统时间
			Date sysTime = new Date();
			user.setUpdatetime(sysTime);
			if (CommonConst.DEFAULT_PWD_CHANGED.equals(user.getPwdChanged())) {
				user.setPassword(Utils.getMD5(user.getPassword()));
			} else {
				user.setPassword(null);
			}
			this.sysUsersDao.updateUser(user);
			// 追加用户权限
			String userid = user.getUserid().toString();
			this.sysUsersDao.deleUserRoles(userid);
			List<TUserRole> userRoles = user.getUserRoles();
			if (userRoles == null || userRoles.isEmpty()) {
				TUserRole userRole = new TUserRole();
				userRole.setUserId(userid);
				userRole.setRoleId(CommonConst.DEFAULT_DELETE_ROLE_ID);
				userRole.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
				userRole.setCreateDateTime(sysTime);
				userRole.setUpdateDateTime(sysTime);
				userRoleDao.insertSelective(userRole);
			} else {
				for (TUserRole userRole : userRoles) {
					userRole.setUserId(userid);
					userRole.setCreateDateTime(sysTime);
					userRole.setUpdateDateTime(sysTime);
					userRole.setDeleted(CommonConst.DEFAULT_DELETE_FLG_0);
					userRoleDao.insertSelective(userRole);
				}
			}
			rslt.success();
		}
		return rslt;
	}

	public Map<String, Object> userLogin(String userName, String passWd) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("username", userName);
		para.put("password", passWd);
		UserInfo user = this.commonDao.getUserInfo(para);
		if (user != null) {
			ret.put("UserInfo", user);
			List<Map<String, Object>> info = this.sysUsersDao.selectRoleMenuByGroupId(user.getGroupId().split(","));
			if (info != null && !info.isEmpty()) {
				// vue-router
				List<Map<String, Object>> routers = new ArrayList<Map<String, Object>>();
				Map<String, Object> parentMenu = new HashMap<String, Object>();
				List<Map<String, Object>> childMenus = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < info.size(); i++) {
					Map<String, Object> nowRecord = info.get(i);
					// 父菜单的情况
					if ("0".equals(nowRecord.get("parentid").toString())) {
						// vue-router: path
						parentMenu.put("path", nowRecord.get("url").toString());
						// vue-router: name
						parentMenu.put("name", nowRecord.get("name").toString());
						// vue-router: icon
						parentMenu.put("icon", nowRecord.get("icon").toString());
						// vue-router: component
						parentMenu.put("component", "home");
						// vue-meta: permission action
						parentMenu.put("meta", nowRecord.get("paction").toString());
					} else {
						Map<String, Object> childMenu = new HashMap<String, Object>();
						String tempStr = nowRecord.get("url").toString().substring(1);
						// vue-router: path
						childMenu.put("path", tempStr);
						// vue-router: name
						childMenu.put("name", nowRecord.get("name").toString());
						// vue-router: icon
						childMenu.put("icon", nowRecord.get("icon").toString());
						// vue-router: component
						childMenu.put("component", tempStr);
						// vue-metra: permission action
						childMenu.put("meta", nowRecord.get("paction").toString());
						childMenus.add(childMenu);
					}
					// Last Record
					if (i == info.size() - 1) {
						parentMenu.put("children", childMenus);
						routers.add(parentMenu);
					} else {
						Map<String, Object> nexRecord = info.get(i + 1);
						if ("0".equals(nexRecord.get("parentid").toString())) {
							parentMenu.put("children", childMenus);
							routers.add(parentMenu);
							parentMenu = new HashMap<String, Object>();
							childMenus = new ArrayList<Map<String, Object>>();
						}
					}
				}
				ret.put("routers", routers);
			}
			TActlog actLog = new TActlog();
			actLog.setActlogId(null);
			actLog.setUrl("/actlogin");
			actLog.setStat("N");
			actLog.setUsername(userName);
			actLog.setAccesstime(Utils.dateTimeToStr());
			actLogDao.insert(actLog);

		} else {
			ret = null;
		}
		return ret;

	}

	/**
	 * 用户查询
	 * 
	 * @param Map<String,
	 *            Object> list
	 * 
	 */
	@Override
	public Response getUsers(Map<String, Object> list) {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		// 分页检索条件初始化
		PaginationUtils.initSelectParam(list);
		// 总数据条数
		int totalCount = this.sysUsersDao.getUsersCount(list);
		ret.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<Map<String, Object>> userInfoList = sysUsersDao.getUsers(list);
			int index = Integer.parseInt(list.get("page").toString());
			for (Map<String, Object> user : userInfoList) {
				user.put("index", ++index);
			}
			ret.put("userInfoList", userInfoList);
		}
		ret.put("columns", Utils.getColumns(CommonConst.PAGE_FLG_USERCOLUMNS));
		rslt.success(ret);
		return rslt;
	}

	
	public Response getUserAuthOptions(Map<String, Object> list){
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("roles", this.commonDao.getRoles());
		ret.put("organizations", this.commonDao.getOrganizations());
		rslt.success(ret);
		return rslt;
	}

}