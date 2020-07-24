package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TUrine {
    private Long urineId;

    private String customerId;

    private Date inspectionTime;

    private String pro;

    private String bld;

    private String nit;

    private String glu;

    private String ket;

    public Long getUrineId() {
        return urineId;
    }

    public void setUrineId(Long urineId) {
        this.urineId = urineId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getBld() {
        return bld;
    }

    public void setBld(String bld) {
        this.bld = bld;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}