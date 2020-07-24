package com.wicresoft.ghealth.common.dto;

import java.util.Date;
import java.util.List;

public class PhysicalInfo {
    private Long physicalId;

    private String customerId;

    private Date customerCheckin;
    
    private String customerCheckinDsp;

    private Date physicalTime;
    
    private String physicalTimeDsp;

    private String height;

    private String weight;

    private String breech;

    private String sbp;

    private String dbp;

    private String glu;

    private String tlc;

    private String hdl;

    private String ldl;

    private String sgpt;

    private String sgot;

    private String alb;

    private String tbil;

    private String dbil;

    private String hbalc;

    private String scre;

    private String bun;

    private String bk;

    private String natrium;
    
    private List<PhysicalDetailInfo> physicalDetailInfos;

    public Long getPhysicalId() {
        return physicalId;
    }

    public void setPhysicalId(Long physicalId) {
        this.physicalId = physicalId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getPhysicalTime() {
        return physicalTime;
    }

    public void setPhysicalTime(Date physicalTime) {
        this.physicalTime = physicalTime;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBreech() {
        return breech;
    }

    public void setBreech(String breech) {
        this.breech = breech;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }

    public String getTlc() {
        return tlc;
    }

    public void setTlc(String tlc) {
        this.tlc = tlc;
    }

    public String getHdl() {
        return hdl;
    }

    public void setHdl(String hdl) {
        this.hdl = hdl;
    }

    public String getLdl() {
        return ldl;
    }

    public void setLdl(String ldl) {
        this.ldl = ldl;
    }

    public String getSgpt() {
        return sgpt;
    }

    public void setSgpt(String sgpt) {
        this.sgpt = sgpt;
    }

    public String getSgot() {
        return sgot;
    }

    public void setSgot(String sgot) {
        this.sgot = sgot;
    }

    public String getAlb() {
        return alb;
    }

    public void setAlb(String alb) {
        this.alb = alb;
    }

    public String getTbil() {
        return tbil;
    }

    public void setTbil(String tbil) {
        this.tbil = tbil;
    }

    public String getDbil() {
        return dbil;
    }

    public void setDbil(String dbil) {
        this.dbil = dbil;
    }

    public String getHbalc() {
        return hbalc;
    }

    public void setHbalc(String hbalc) {
        this.hbalc = hbalc;
    }

    public String getScre() {
        return scre;
    }

    public void setScre(String scre) {
        this.scre = scre;
    }

    public String getBun() {
        return bun;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public String getBk() {
        return bk;
    }

    public void setBk(String bk) {
        this.bk = bk;
    }

    public String getNatrium() {
        return natrium;
    }

    public void setNatrium(String natrium) {
        this.natrium = natrium;
    }

	/**
	 * @return the customerCheckin
	 */
	public Date getCustomerCheckin() {
		return customerCheckin;
	}

	/**
	 * @param customerCheckin the customerCheckin to set
	 */
	public void setCustomerCheckin(Date customerCheckin) {
		this.customerCheckin = customerCheckin;
	}

	/**
	 * @return the customerCheckinDsp
	 */
	public String getCustomerCheckinDsp() {
		return customerCheckinDsp;
	}

	/**
	 * @param customerCheckinDsp the customerCheckinDsp to set
	 */
	public void setCustomerCheckinDsp(String customerCheckinDsp) {
		this.customerCheckinDsp = customerCheckinDsp;
	}

	/**
	 * @return the physicalTimeDsp
	 */
	public String getPhysicalTimeDsp() {
		return physicalTimeDsp;
	}

	/**
	 * @param physicalTimeDsp the physicalTimeDsp to set
	 */
	public void setPhysicalTimeDsp(String physicalTimeDsp) {
		this.physicalTimeDsp = physicalTimeDsp;
	}

	/**
	 * @return the physicalDetailInfos
	 */
	public List<PhysicalDetailInfo> getPhysicalDetailInfos() {
		return physicalDetailInfos;
	}

	/**
	 * @param physicalDetailInfos the physicalDetailInfos to set
	 */
	public void setPhysicalDetailInfos(List<PhysicalDetailInfo> physicalDetailInfos) {
		this.physicalDetailInfos = physicalDetailInfos;
	}

}