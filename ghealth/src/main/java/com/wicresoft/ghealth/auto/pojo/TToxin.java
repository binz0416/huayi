package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TToxin {
    private Long toxinId;

    private String customerId;

    private Date inspectionTime;

    private String path;

    public Long getToxinId() {
        return toxinId;
    }

    public void setToxinId(Long toxinId) {
        this.toxinId = toxinId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}