package com.wicresoft.ghealth.auto.pojo;

public class TEPCustomerData {
    private Long epcustomerId;

    private String noOfEmployees;

    private String description;

    public Long getEpcustomerId() {
        return epcustomerId;
    }

    public void setEpcustomerId(Long epcustomerId) {
        this.epcustomerId = epcustomerId;
    }

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees == null ? null : noOfEmployees.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}