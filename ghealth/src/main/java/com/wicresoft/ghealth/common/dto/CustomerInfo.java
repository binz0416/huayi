package com.wicresoft.ghealth.common.dto;

import java.util.Date;
import java.util.List;

public class CustomerInfo {

	private String customerId;
	private String customerName;
	private String customerIdcard;
	private String customerMobile;
	private int customerFaceid;
	private String customerBirthday;
	private String customerSex;
	private String customerSexDsp;
	private Date customerLastCheckin;
	private String customerLastCheckinDsp;
	private int customerAge;
	List<CustomerCheckinInfo> checkinInfos;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerIdcard() {
		return customerIdcard;
	}

	public void setCustomerIdcard(String customerIdcard) {
		this.customerIdcard = customerIdcard;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public int getCustomerFaceid() {
		return customerFaceid;
	}

	public void setCustomerFaceid(int customerFaceid) {
		this.customerFaceid = customerFaceid;
	}

	public String getCustomerBirthday() {
		return customerBirthday;
	}

	public void setCustomerBirthday(String customerBirthday) {
		this.customerBirthday = customerBirthday;
	}

	public String getCustomerSex() {
		return customerSex;
	}

	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}

	public List<CustomerCheckinInfo> getCheckinInfos() {
		return checkinInfos;
	}

	public void setCheckinInfos(List<CustomerCheckinInfo> checkinInfos) {
		this.checkinInfos = checkinInfos;
	}

	public String getCustomerSexDsp() {
		return customerSexDsp;
	}

	public void setCustomerSexDsp(String customerSexDsp) {
		this.customerSexDsp = customerSexDsp;
	}

	public Date getCustomerLastCheckin() {
		return customerLastCheckin;
	}

	public void setCustomerLastCheckin(Date customerLastCheckin) {
		this.customerLastCheckin = customerLastCheckin;
	}

	public String getCustomerLastCheckinDsp() {
		return customerLastCheckinDsp;
	}

	public void setCustomerLastCheckinDsp(String customerLastCheckinDsp) {
		this.customerLastCheckinDsp = customerLastCheckinDsp;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

}
