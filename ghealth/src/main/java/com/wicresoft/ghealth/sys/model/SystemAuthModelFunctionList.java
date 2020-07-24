package com.wicresoft.ghealth.sys.model;

import java.util.LinkedHashMap;
import java.util.List;

public class SystemAuthModelFunctionList {
	private String menuid;

	private String name;
	
	private String parentid;
	
	private List<LinkedHashMap<String,String>> action;
	
	private List<LinkedHashMap<String,String>> auth;

	public String getMenuid() {
		return menuid;
	}

	
	public List<LinkedHashMap<String, String>> getAction() {
		return action;
	}


	public void setAction(List<LinkedHashMap<String, String>> action) {
		this.action = action;
	}


	public List<LinkedHashMap<String, String>> getAuth() {
		return auth;
	}


	public void setAuth(List<LinkedHashMap<String, String>> auth) {
		this.auth = auth;
	}


	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	
	
	
}
