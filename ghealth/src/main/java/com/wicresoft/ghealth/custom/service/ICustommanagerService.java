package com.wicresoft.ghealth.custom.service;

import java.util.Map;

import com.wicresoft.ghealth.common.Response;

public interface ICustommanagerService {
	
	public Response hotel(Map<String, Object> hotel);
	
	public Response selectAll(Map<String, Object> list);
	
}
