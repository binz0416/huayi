package com.wicresoft.ghealth.sys.dto;

import java.util.Date;
import java.util.List;

public class RoleMenu {
    private Long connectionid;

    private Long roleId;

    private String menuid;

    private String actions;

    private Date createDateTime;

    private Date updateDateTime;

    private String deleted;
    
    private boolean checked;
    
    private boolean expand;
    
    private boolean indeterminate;
    
    private boolean selected;
    
    private String title;
    
    private String nodeKey;
    
    private List<RoleMenu> children;

	/**
	 * @return the connectionid
	 */
	public Long getConnectionid() {
		return connectionid;
	}

	/**
	 * @param connectionid the connectionid to set
	 */
	public void setConnectionid(Long connectionid) {
		this.connectionid = connectionid;
	}

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
	 * @return the actions
	 */
	public String getActions() {
		return actions;
	}

	/**
	 * @param actions the actions to set
	 */
	public void setActions(String actions) {
		this.actions = actions;
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
	 * @return the checked
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * @param checked the checked to set
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * @return the menuid
	 */
	public String getMenuid() {
		return menuid;
	}

	/**
	 * @param menuid the menuid to set
	 */
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	/**
	 * @return the children
	 */
	public List<RoleMenu> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<RoleMenu> children) {
		this.children = children;
	}

	/**
	 * @return the expand
	 */
	public boolean isExpand() {
		return expand;
	}

	/**
	 * @param expand the expand to set
	 */
	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	/**
	 * @return the indeterminate
	 */
	public boolean isIndeterminate() {
		return indeterminate;
	}

	/**
	 * @param indeterminate the indeterminate to set
	 */
	public void setIndeterminate(boolean indeterminate) {
		this.indeterminate = indeterminate;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the nodeKey
	 */
	public String getNodeKey() {
		return nodeKey;
	}

	/**
	 * @param nodeKey the nodeKey to set
	 */
	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}


}