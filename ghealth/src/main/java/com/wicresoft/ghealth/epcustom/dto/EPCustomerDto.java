package com.wicresoft.ghealth.epcustom.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class EPCustomerDto {
    private Long epcustomerId;
    // 负责人
    @NotNull(message = "epCustom.ownerUserid.isempty")
    private Long ownerUserid;
    // 负责人姓名
    private String ownerUserName;
    // 创建人
    private Long creatorUserid;
    // 创建者姓名
    private String creatorUserName;

    private Long contactsId;
    // 首要联系人
    private String contactsName;
    // 客户名称
    @NotBlank(message = "epCustom.name.isempty")
    private String name;
    // 客户信息来源
    private String origin;
    // 联系地址
    private String address;
    // 邮编
    private String zipCode;
    // 客户行业
    private String industry;
    // 年营业额
    private String annualRevenue;
    // 公司性质
    private String ownership;
    // 评分
    private String rating;

    private Integer createTime;
    
    private String createTimeStr;

    private Integer updateTime;
    
    private String updateTimeStr;
    // 到期天数
    private String maturityDate;

    private Integer isDeleted;
    // 是否锁定
    private Integer isLocked;

    private Long deleteUserid;

    private Integer deleteTime;
    // 员工人数
    private String noOfEmployees;
    // 备注
    private String description;
    
    private List<EPContactsDto> contactsList;
    
    private String apiflag;
    
    private Integer creatCount;
    
    private Integer ownerCount;

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

	/**
	 * @return the ownerUserName
	 */
	public String getOwnerUserName() {
		return ownerUserName;
	}

	/**
	 * @param ownerUserName the ownerUserName to set
	 */
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}

	/**
	 * @return the creatorUserName
	 */
	public String getCreatorUserName() {
		return creatorUserName;
	}

	/**
	 * @param creatorUserName the creatorUserName to set
	 */
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}

	/**
	 * @return the contactsName
	 */
	public String getContactsName() {
		return contactsName;
	}

	/**
	 * @param contactsName the contactsName to set
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	/**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	/**
	 * @return the updateTimeStr
	 */
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	/**
	 * @param updateTimeStr the updateTimeStr to set
	 */
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	/**
	 * @return the maturityDate
	 */
	public String getMaturityDate() {
		return maturityDate;
	}

	/**
	 * @param maturityDate the maturityDate to set
	 */
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	/**
	 * @return the noOfEmployees
	 */
	public String getNoOfEmployees() {
		return noOfEmployees;
	}

	/**
	 * @param noOfEmployees the noOfEmployees to set
	 */
	public void setNoOfEmployees(String noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the contactsList
	 */
	public List<EPContactsDto> getContactsList() {
		return contactsList;
	}

	/**
	 * @param contactsList the contactsList to set
	 */
	public void setContactsList(List<EPContactsDto> contactsList) {
		this.contactsList = contactsList;
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

	/**
	 * @return the creatCount
	 */
	public Integer getCreatCount() {
		return creatCount;
	}

	/**
	 * @param creatCount the creatCount to set
	 */
	public void setCreatCount(Integer creatCount) {
		this.creatCount = creatCount;
	}

	/**
	 * @return the ownerCount
	 */
	public Integer getOwnerCount() {
		return ownerCount;
	}

	/**
	 * @param ownerCount the ownerCount to set
	 */
	public void setOwnerCount(Integer ownerCount) {
		this.ownerCount = ownerCount;
	}

}