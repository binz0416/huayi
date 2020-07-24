package com.wicresoft.ghealth.acquisition.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.wicresoft.ghealth.common.BusinessException;
import com.wicresoft.ghealth.common.Response;

public interface  ISpacecapsuleService {

	public Response list(@RequestBody Map<String, Object> list);

	public Response add(Map<String, Object> add) throws BusinessException;

	public Response edit(Map<String, Object> edit) throws BusinessException;

	public Response dele(Map<String, Object> dele) throws BusinessException;
}
