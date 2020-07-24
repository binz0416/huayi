package com.wicresoft.ghealth.acquisition.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class ToxinAbnormal {
	
	
	private Long abnormalId;
	
	
	
	private Long toxinId;
	
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getToxinId() {
		return toxinId;
	}

	public void setToxinId(Long toxinId) {
		this.toxinId = toxinId;
	}

	public Long getAbnormalId() {
		return abnormalId;
	}

	public void setAbnormalId(Long abnormalId) {
		this.abnormalId = abnormalId;
	}
    
   
}