package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TPhysicalUltrasound {
    private Long ultrasoundId;

    private String customerId;

    private Date inspectionTime;

    private String clinicalDiagnosis;

    private String checkpoint;

    public Long getUltrasoundId() {
        return ultrasoundId;
    }

    public void setUltrasoundId(Long ultrasoundId) {
        this.ultrasoundId = ultrasoundId;
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

    public String getClinicalDiagnosis() {
        return clinicalDiagnosis;
    }

    public void setClinicalDiagnosis(String clinicalDiagnosis) {
        this.clinicalDiagnosis = clinicalDiagnosis;
    }

    public String getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(String checkpoint) {
        this.checkpoint = checkpoint;
    }
}