package com.wicresoft.ghealth.sys.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class Role {

    private Long roleId;

	@NotBlank(message = "role.roleName.isempty")
    private String roleName;

	@NotBlank(message = "role.roleShortname.isempty")
    private String roleShortname;

    private String roleDescription;

    private Date createDateTime;

    private Date updateDateTime;

    private String deleted;
    
    private String apiflg;
    
    private List<RoleMenu> roleMenus;

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleShortname
	 */
	public String getRoleShortname() {
		return roleShortname;
	}

	/**
	 * @param roleShortname the roleShortname to set
	 */
	public void setRoleShortname(String roleShortname) {
		this.roleShortname = roleShortname;
	}

	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}

	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * @return the createDateTime
	 */
	public Date getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the roleMenus
	 */
	public List<RoleMenu> getRoleMenus() {
		return roleMenus;
	}

	/**
	 * @param roleMenus the roleMenus to set
	 */
	public void setRoleMenus(List<RoleMenu> roleMenus) {
		this.roleMenus = roleMenus;
	}

	/**
	 * @return the apiflg
	 */
	public String getApiflg() {
		return apiflg;
	}

	/**
	 * @param apiflg the apiflg to set
	 */
	public void setApiflg(String apiflg) {
		this.apiflg = apiflg;
	}


}