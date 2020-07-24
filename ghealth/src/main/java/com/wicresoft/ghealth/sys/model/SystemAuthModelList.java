package com.wicresoft.ghealth.sys.model;

import java.util.List;

import com.wicresoft.ghealth.sys.model.SystemAuthModelFunctionList;

public class SystemAuthModelList {
	private String groupid;

	private String groupname;
	
	private String groupshortname;
	
	private List<SystemAuthModelFunctionList> functions;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGroupshortname() {
		return groupshortname;
	}

	public void setGroupshortname(String groupshortname) {
		this.groupshortname = groupshortname;
	}

	public List<SystemAuthModelFunctionList> getFunctions() {
		return functions;
	}

	public void setFunctions(List<SystemAuthModelFunctionList> functions) {
		this.functions = functions;
	}
	
	
}
