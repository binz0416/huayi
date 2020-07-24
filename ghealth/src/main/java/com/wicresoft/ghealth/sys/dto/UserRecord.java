package com.wicresoft.ghealth.sys.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.wicresoft.ghealth.auto.pojo.TUserRole;

public class UserRecord {

	private String apiflg;

	private Long userid;

	private String organizationId;

	@NotBlank(message = "user.username.isempty")
	private String username;
	
	@NotBlank(message = "user.password.isempty")
	//@Pattern(regexp="/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$/", message="user.password.format")
	private String password;
	
	private String pwdChanged;

	private String fullname;

	private String sex;

	@NotBlank(message = "user.email.isempty")
	@Email(message = "user.email.format")
	private String email;

	@Pattern(regexp="^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", message="user.tel.format")
	private String tel;

	private String position;

	private Date createtime;

	private String updateuser;

	private Date updatetime;
	
	private List<TUserRole> userRoles;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getApiflg() {
		return apiflg;
	}

	public void setApiflg(String apiflg) {
		this.apiflg = apiflg;
	}

	public List<TUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<TUserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getPwdChanged() {
		return pwdChanged;
	}

	public void setPwdChanged(String pwdChanged) {
		this.pwdChanged = pwdChanged;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}