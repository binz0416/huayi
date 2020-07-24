package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TUserRole {
    private Long connectionid;

    private String userId;

    private Long roleId;

    private Date createDateTime;

    private Date updateDateTime;

    private String deleted;

    public Long getConnectionid() {
        return connectionid;
    }

    public void setConnectionid(Long connectionid) {
        this.connectionid = connectionid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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