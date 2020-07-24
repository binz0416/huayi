package com.wicresoft.ghealth.acquisition.service;
import java.util.Map;

import com.wicresoft.ghealth.common.Response;

public interface  IWearableService {

	public Response heartbook();
	
	public Response getHeartBySN(Map<String, Object> machine);
}
