package com.wicresoft.ghealth.authorization.exception;

/**
 * 方法不支持当前环境的异常，主要跟一些配置参数有关
 * @author binz
 * @date 2017/07/07
 */
public class MethodNotSupportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MethodNotSupportException(String message) {
        super(message);
    }
}
