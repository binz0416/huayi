package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TCatering {
    private Long cateringId;

    private String customerId;

    private Long setMealId;

    private Integer customerFaceid;

    private String customerName;

    private Date captureTime;

    private String cameraNo;

    private Integer valid;

    public Long getCateringId() {
        return cateringId;
    }

    public void setCateringId(Long cateringId) {
        this.cateringId = cateringId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Long setMealId) {
        this.setMealId = setMealId;
    }

    public Integer getCustomerFaceid() {
        return customerFaceid;
    }

    public void setCustomerFaceid(Integer customerFaceid) {
        this.customerFaceid = customerFaceid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getCameraNo() {
        return cameraNo;
    }

    public void setCameraNo(String cameraNo) {
        this.cameraNo = cameraNo;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}