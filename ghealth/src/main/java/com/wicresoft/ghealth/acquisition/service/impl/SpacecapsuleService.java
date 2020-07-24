package com.wicresoft.ghealth.acquisition.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wicresoft.ghealth.acquisition.service.ISpacecapsuleService;
import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

@Service("spacecapsuleService")
public class SpacecapsuleService implements ISpacecapsuleService {

	@Override
	public Response list(@RequestBody Map<String, Object> list) {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();
		return rslt;
	}

	@Override
	public Response add(Map<String, Object> add) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response edit(Map<String, Object> edit) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		rslt.success(ret);
		return rslt;
	}

	@Override
	public Response dele(Map<String, Object> dele) throws BusinessException {
		Response rslt = new Response();
		Map<String, Object> ret = new HashMap<String, Object>();

		rslt.success(ret);
		return rslt;
	}

}
