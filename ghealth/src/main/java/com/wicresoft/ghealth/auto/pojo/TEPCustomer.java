package com.wicresoft.ghealth.auto.pojo;

public class TEPCustomer {
    private Long epcustomerId;

    private Long ownerUserid;

    private Long creatorUserid;

    private Long contactsId;

    private String name;

    private String origin;

    private String address;

    private String zipCode;

    private String industry;

    private String annualRevenue;

    private String ownership;

    private String rating;

    private Integer createTime;

    private Integer updateTime;

    private Integer isDeleted;

    private Integer isLocked;

    private Long deleteUserid;

    private Integer deleteTime;

    public Long getEpcustomerId() {
        return epcustomerId;
    }

    public void setEpcustomerId(Long epcustomerId) {
        this.epcustomerId = epcustomerId;
    }

    public Long getOwnerUserid() {
        return ownerUserid;
    }

    public void setOwnerUserid(Long ownerUserid) {
        this.ownerUserid = ownerUserid;
    }

    public Long getCreatorUserid() {
        return creatorUserid;
    }

    public void setCreatorUserid(Long creatorUserid) {
        this.creatorUserid = creatorUserid;
    }

    public Long getContactsId() {
        return contactsId;
    }

    public void setContactsId(Long contactsId) {
        this.contactsId = contactsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue == null ? null : annualRevenue.trim();
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Long getDeleteUserid() {
        return deleteUserid;
    }

    public void setDeleteUserid(Long deleteUserid) {
        this.deleteUserid = deleteUserid;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }
}