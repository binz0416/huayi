package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TCustomerCheckin {
    private Long connectionid;

    private String customerId;

    private Date customerCheckin;

    private String organizationId;

    private String hotelRoomnum;

    private Date customerCheckout;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getHotelRoomnum() {
        return hotelRoomnum;
    }

    public void setHotelRoomnum(String hotelRoomnum) {
        this.hotelRoomnum = hotelRoomnum;
    }

    public Date getCustomerCheckout() {
        return customerCheckout;
    }

    public void setCustomerCheckout(Date customerCheckout) {
        this.customerCheckout = customerCheckout;
    }
}