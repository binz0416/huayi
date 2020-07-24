package com.wicresoft.ghealth.epcustom.dto;

import javax.validation.Valid;

public class EPCommonDto {

	@Valid
	private EPCustomerDto epCustomerDto;
	@Valid
	private EPContactsDto epContactsDto;
	
	private String apiflag;

	/**
	 * @return the epCustomerDto
	 */
	public EPCustomerDto getEpCustomerDto() {
		return epCustomerDto;
	}

	/**
	 * @param epCustomerDto the epCustomerDto to set
	 */
	public void setEpCustomerDto(EPCustomerDto epCustomerDto) {
		this.epCustomerDto = epCustomerDto;
	}

	/**
	 * @return the epContactsDto
	 */
	public EPContactsDto getEpContactsDto() {
		return epContactsDto;
	}

	/**
	 * @param epContactsDto the epContactsDto to set
	 */
	public void setEpContactsDto(EPContactsDto epContactsDto) {
		this.epContactsDto = epContactsDto;
	}

	/**
	 * @return the apiflag
	 */
	public String getApiflag() {
		return apiflag;
	}

	/**
	 * @param apiflag the apiflag to set
	 */
	public void setApiflag(String apiflag) {
		this.apiflag = apiflag;
	}
	
	
}
