package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.InputAddress;

public interface AddressService {
	public List<InputAddress> selectSido();
	public List<InputAddress> selectGugun(String sidocode);
	public List<InputAddress> selectDong(String guguncode);
}
