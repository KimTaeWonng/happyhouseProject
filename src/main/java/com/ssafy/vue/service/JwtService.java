package com.ssafy.vue.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean isUsable(String jwt);
	
}
