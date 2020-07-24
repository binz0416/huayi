package com.wicresoft.ghealth.sys.model;

import java.util.List;

public class LoginUserMenuAuthorityInfoSub {
   
	private String id;
	
	private String icon;
	
	private String name;
	
	private String url;
	
	private List<String> action;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getAction() {
		return action;
	}

	public void setAction(List<String> action) {
		this.action = action;
	}
	
	
}