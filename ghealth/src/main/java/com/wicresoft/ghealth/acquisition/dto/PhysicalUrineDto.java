package com.wicresoft.ghealth.acquisition.dto;

import java.util.Date;

public class PhysicalUrineDto {

	private Long urineId;
	
	private String customerId;
	
	private Date inspectionTime;
	
	private String inspectionTimeDsp;
	
	//private String specimenSpecies;
	
	private String pro;
	
	private String bld;
	
	private String nit;
	
	private String glu;
	
	private String ket;

	/**
	 * @return the urineId
	 */
	public Long getUrineId() {
		return urineId;
	}

	/**
	 * @param urineId the urineId to set
	 */
	public void setUrineId(Long urineId) {
		this.urineId = urineId;
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
	 * @return the inspectionTime
	 */
	public Date getInspectionTime() {
		return inspectionTime;
	}

	/**
	 * @param inspectionTime the inspectionTime to set
	 */
	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	/**
	 * @return the pro
	 */
	public String getPro() {
		return pro;
	}

	/**
	 * @param pro the pro to set
	 */
	public void setPro(String pro) {
		this.pro = pro;
	}

	/**
	 * @return the bld
	 */
	public String getBld() {
		return bld;
	}

	/**
	 * @param bld the bld to set
	 */
	public void setBld(String bld) {
		this.bld = bld;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the glu
	 */
	public String getGlu() {
		return glu;
	}

	/**
	 * @param glu the glu to set
	 */
	public void setGlu(String glu) {
		this.glu = glu;
	}

	/**
	 * @return the ket
	 */
	public String getKet() {
		return ket;
	}

	/**
	 * @param ket the ket to set
	 */
	public void setKet(String ket) {
		this.ket = ket;
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
