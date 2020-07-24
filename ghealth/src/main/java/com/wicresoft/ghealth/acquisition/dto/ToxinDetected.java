package com.wicresoft.ghealth.acquisition.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class ToxinDetected {
	
	private Long detailId;
	
	
	private Long toxinId;
	
	
	private String method;
	
	
	private String sampleType;
	
	
	private String xenobiotics_detected;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSampleType() {
		return sampleType;
	}
	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}
	public String getXenobiotics_detected() {
		return xenobiotics_detected;
	}
	public void setXenobiotics_detected(String xenobiotics_detected) {
		this.xenobiotics_detected = xenobiotics_detected;
	}
	public Long getToxinId() {
		return toxinId;
	}
	public void setToxinId(Long toxinId) {
		this.toxinId = toxinId;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	} 
	
}