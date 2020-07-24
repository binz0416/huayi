package com.wicresoft.ghealth.acquisition.dto;


public class PhysicalDto {
	
	private String customerId;

	private PhysicalUrineDto urineData;
	
	private PhysicalBloodDto bloodData;
	
	private PhysicalUltrasoundDto ultrasoundData;

	/**
	 * @return the urineData
	 */
	public PhysicalUrineDto getUrineData() {
		return urineData;
	}

	/**
	 * @param urineData the urineData to set
	 */
	public void setUrineData(PhysicalUrineDto urineData) {
		this.urineData = urineData;
	}

	/**
	 * @return the bloodData
	 */
	public PhysicalBloodDto getBloodData() {
		return bloodData;
	}

	/**
	 * @param bloodData the bloodData to set
	 */
	public void setBloodData(PhysicalBloodDto bloodData) {
		this.bloodData = bloodData;
	}

	/**
	 * @return the ultrasoundData
	 */
	public PhysicalUltrasoundDto getUltrasoundData() {
		return ultrasoundData;
	}

	/**
	 * @param ultrasoundData the ultrasoundData to set
	 */
	public void setUltrasoundData(PhysicalUltrasoundDto ultrasoundData) {
		this.ultrasoundData = ultrasoundData;
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
	
}
