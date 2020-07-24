package com.wicresoft.ghealth.catering.model;

import java.util.List;

public class PrintInfo {
	String logo;
	String corporateName; //公司名称
	String numberNo; //餐号
	String mealContent; // 套餐名称
	List<Meals> mealsList; //菜名列表
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public String getNumberNo() {
		return numberNo;
	}
	public void setNumberNo(String numberNo) {
		this.numberNo = numberNo;
	}
	public String getMealContent() {
		return mealContent;
	}
	public void setMealContent(String mealContent) {
		this.mealContent = mealContent;
	}
	public List<Meals> getMealsList() {
		return mealsList;
	}
	public void setMealsList(List<Meals> mealsList) {
		this.mealsList = mealsList;
	}

}
