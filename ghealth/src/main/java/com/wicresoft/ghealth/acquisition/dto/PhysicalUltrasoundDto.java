package com.wicresoft.ghealth.acquisition.dto;

import java.util.Date;

public class PhysicalUltrasoundDto {
	
    private Long ultrasoundId;
    
    private String customerId;

    private Date inspectionTime;
    
    private String inspectionTimeDsp;

    private String clinicalDiagnosis;

    private String checkpoint;
    
    private String ulDescription;

    private String ulTip;

    public Long getUltrasoundId() {
        return ultrasoundId;
    }

    public void setUltrasoundId(Long ultrasoundId) {
        this.ultrasoundId = ultrasoundId;
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

	/**
	 * @return the ulDescription
	 */
	public String getUlDescription() {
		return ulDescription;
	}

	/**
	 * @param ulDescription the ulDescription to set
	 */
	public void setUlDescription(String ulDescription) {
		this.ulDescription = ulDescription;
	}

	/**
	 * @return the ulTip
	 */
	public String getUlTip() {
		return ulTip;
	}

	/**
	 * @param ulTip the ulTip to set
	 */
	public void setUlTip(String ulTip) {
		this.ulTip = ulTip;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the inspectionTimeDsp
	 */
	public String getInspectionTimeDsp() {
		return inspectionTimeDsp;
	}

	/**
	 * @param inspectionTimeDsp the inspectionTimeDsp to set
	 */
	public void setInspectionTimeDsp(String inspectionTimeDsp) {
		this.inspectionTimeDsp = inspectionTimeDsp;
	}
}