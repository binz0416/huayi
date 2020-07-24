package com.wicresoft.ghealth.common.dto;

import java.util.Date;

public class CustomerCheckinInfo {
	
	private Long connectionid;
	private String customerId;
	private Date customerCheckin;
	private String customerCheckinDsp;
	private Date customerCheckout;
	private String customerCheckoutDsp;
	private String hotelName;
	private String hotelRoomnum;
	private String organizationId;

	public Long getConnectionid() {
		return connectionid;
	}

	public void setConnectionid(Long connectionid) {
		this.connectionid = connectionid;
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

	public String getCustomerCheckinDsp() {
		return customerCheckinDsp;
	}

	public void setCustomerCheckinDsp(String customerCheckinDsp) {
		this.customerCheckinDsp = customerCheckinDsp;
	}

	public Date getCustomerCheckout() {
		return customerCheckout;
	}

	public void setCustomerCheckout(Date customerCheckout) {
		this.customerCheckout = customerCheckout;
	}

	public String getCustomerCheckoutDsp() {
		return customerCheckoutDsp;
	}

	public void setCustomerCheckoutDsp(String customerCheckoutDsp) {
		this.customerCheckoutDsp = customerCheckoutDsp;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelRoomnum() {
		return hotelRoomnum;
	}

	public void setHotelRoomnum(String hotelRoomnum) {
		this.hotelRoomnum = hotelRoomnum;
	}

	/**
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}
