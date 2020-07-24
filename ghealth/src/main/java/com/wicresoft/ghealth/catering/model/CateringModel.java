package com.wicresoft.ghealth.catering.model;

import java.util.Date;

public class CateringModel {
	
    private Long cateringId;

    private Long customerId;

    private Long setMealId;

    private Integer faceId;

    private String name;

    private Date captureTime;

    private String cameraNo;

    private String meal;

	public Long getCateringId() {
		return cateringId;
	}

	public void setCateringId(Long cateringId) {
		this.cateringId = cateringId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSetMealId() {
		return setMealId;
	}

	public void setSetMealId(Long setMealId) {
		this.setMealId = setMealId;
	}

	public Integer getFaceId() {
		return faceId;
	}

	public void setFaceId(Integer faceId) {
		this.faceId = faceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}
    
    
}
