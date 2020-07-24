package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TComprehensive {
    private Long comprehensiveId;

    private String customerId;

    private Date inspectionTime;

    private String path;

    private String reportArea;

    public Long getComprehensiveId() {
        return comprehensiveId;
    }

    public void setComprehensiveId(Long comprehensiveId) {
        this.comprehensiveId = comprehensiveId;
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

    public String getReportArea() {
        return reportArea;
    }

    public void setReportArea(String reportArea) {
        this.reportArea = reportArea;
    }
}