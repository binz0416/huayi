package com.wicresoft.ghealth.acquisition.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class Toxin {
	private Long toxinId;

	@NotBlank(message = "toxin.customerId.isempty")
	private String customerId;

	private Date customerCheckin;

	private String customerCheckinStr;

	private String path;

	private String no;

	private Date inspectionTime;

	private String expertOpinion;

	@NotBlank(message = "toxin.reportArea.isempty")
	private String reportArea;

	private List<ToxinDetected> toxinDetected;
	
	private List<ToxinAbnormal> toxinAbnormal;
	
	private String suggest;
	
	private String food;

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

	public Date getCustomerCheckin() {
		return customerCheckin;
	}

	public void setCustomerCheckin(Date customerCheckin) {
		this.customerCheckin = customerCheckin;
	}

	public String getCustomerCheckinStr() {
		return customerCheckinStr;
	}

	public void setCustomerCheckinStr(String customerCheckinStr) {
		this.customerCheckinStr = customerCheckinStr;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	public String getExpertOpinion() {
		return expertOpinion;
	}

	public void setExpertOpinion(String expertOpinion) {
		this.expertOpinion = expertOpinion;
	}

	public String getReportArea() {
		return reportArea;
	}

	public void setReportArea(String reportArea) {
		this.reportArea = reportArea;
	}

	public List<ToxinDetected> getToxinDetected() {
		return toxinDetected;
	}

	public void setToxinDetected(List<ToxinDetected> toxinDetected) {
		this.toxinDetected = toxinDetected;
	}

	public List<ToxinAbnormal> getToxinAbnormal() {
		return toxinAbnormal;
	}

	public void setToxinAbnormal(List<ToxinAbnormal> toxinAbnormal) {
		this.toxinAbnormal = toxinAbnormal;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

}