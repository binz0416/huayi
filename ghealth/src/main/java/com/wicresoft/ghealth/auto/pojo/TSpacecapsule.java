package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TSpacecapsule {
    private Long spacecapsuleId;

    private String customerId;

    private Date customerCheckin;

    private String reportId;

    private String machineSn;

    private Date startTime;

    private Date endTime;

    public Long getSpacecapsuleId() {
        return spacecapsuleId;
    }

    public void setSpacecapsuleId(Long spacecapsuleId) {
        this.spacecapsuleId = spacecapsuleId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getCustomerCheckin() {
        return customerCheckin;
    }

    public void setCustomerCheckin(Date customerCheckin) {
        this.customerCheckin = customerCheckin;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getMachineSn() {
        return machineSn;
    }

    public void setMachineSn(String machineSn) {
        this.machineSn = machineSn;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}