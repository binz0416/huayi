package com.wicresoft.ghealth.authorization.repository.impl;

import com.wicresoft.ghealth.authorization.repository.UserModelRepository;
import com.wicresoft.ghealth.common.dto.UserInfo;

/**
 * @author binz
 * @date 2017/07/07.
 */
@SuppressWarnings("rawtypes")
public class UserRepository implements UserModelRepository {
	@Override
	public Object getCurrentUser(String key) {
		UserInfo user = new UserInfo();
		user.setUsername(key);
		return user;
	}
}