package com.wicresoft.ghealth.sys.model;

import com.wicresoft.ghealth.sys.model.LoginUserMenuAuthorityInfoSub;
import java.util.List;

public class LoginUserMenuAuthorityInfo {
   
	private String id;
	
	private String icon;
	
	private String name;
	
	private List<LoginUserMenuAuthorityInfoSub> menuItems;

	

	public List<LoginUserMenuAuthorityInfoSub> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<LoginUserMenuAuthorityInfoSub> menuItems) {
		this.menuItems = menuItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}