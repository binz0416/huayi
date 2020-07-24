package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TRoleMenu {
    private Long connectionid;

    private Long roleId;

    private String menuId;

    private String permissionAction;

    private Date createDateTime;

    private Date updateDateTime;

    private String deleted;

    public Long getConnectionid() {
        return connectionid;
    }

    public void setConnectionid(Long connectionid) {
        this.connectionid = connectionid;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPermissionAction() {
        return permissionAction;
    }

    public void setPermissionAction(String permissionAction) {
        this.permissionAction = permissionAction;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}