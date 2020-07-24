package com.wicresoft.ghealth.epcustom.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class EPContactsDto {
	// 客户联系人
    private Long contactsId;
    // 客户联系人姓名
    @NotBlank(message = "epContact.name.isempty")
    private String name;
    // 创建者Id
    private Long creatorUserid;
    // 创建者姓名
    private String creatorUserName;
    // 所属客户Id
    @NotNull(message = "epContact.epcustomerId.isempty")
    private Long epcustomerId;
    // 所属客户名称
    private String epcustomerName;
    // 客户联系人岗位
    private String post;
    // 客户联系人部门
    private String department;
    // 性别
    private Integer sex;
    
    private String sexDsp;
    // 称呼
    private String saltname;
    // 联系人电话
    @Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", message="epContact.tel.format")
    private String telephone;
    // 联系人邮箱
	@Email(message = "epContact.email.format")
    private String email;
    // qq
    private String qqNo;
    // 联系地址
    private String address;
    // 邮编
    private String zipCode;
    // 联系人信息备注
    private String description;
    // 客户-联系人关联Id
    private Long relationId;

    private Integer createTime;
    
    private String createTimeStr;

    private Integer updateTime;
    
    private String updateTimeStr;

    private Integer isDeleted;

    private Long deleteUserid;

    private Integer deleteTime;
    
    private String apiflag;

    public Long getContactsId() {
        return contactsId;
    }

    public void setContactsId(Long contactsId) {
        this.contactsId = contactsId;
    }

    public Long getCreatorUserid() {
        return creatorUserid;
    }

    public void setCreatorUserid(Long creatorUserid) {
        this.creatorUserid = creatorUserid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSaltname() {
        return saltname;
    }

    public void setSaltname(String saltname) {
        this.saltname = saltname == null ? null : saltname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo == null ? null : qqNo.trim();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
	 * @return the sexDsp
	 */
	public String getSexDsp() {
		return sexDsp;
	}

	/**
	 * @param sexDsp the sexDsp to set
	 */
	public void setSexDsp(String sexDsp) {
		this.sexDsp = sexDsp;
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
	 * @return the epcustomerId
	 */
	public Long getEpcustomerId() {
		return epcustomerId;
	}

	/**
	 * @param epcustomerId the epcustomerId to set
	 */
	public void setEpcustomerId(Long epcustomerId) {
		this.epcustomerId = epcustomerId;
	}

	/**
	 * @return the epcustomerName
	 */
	public String getEpcustomerName() {
		return epcustomerName;
	}

	/**
	 * @param epcustomerName the epcustomerName to set
	 */
	public void setEpcustomerName(String epcustomerName) {
		this.epcustomerName = epcustomerName;
	}

	/**
	 * @return the relationId
	 */
	public Long getRelationId() {
		return relationId;
	}

	/**
	 * @param relationId the relationId to set
	 */
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
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