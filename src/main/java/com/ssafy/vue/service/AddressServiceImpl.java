package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.InputAddress;
import com.ssafy.vue.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<InputAddress> selectSido() {
		// TODO Auto-generated method stub
		return addressMapper.selectSidoInput();
	}

	@Override
	public List<InputAddress> selectGugun(String sidocode) {
		// TODO Auto-generated method stub
		return addressMapper.selectGugunInput(sidocode);
	}

	@Override
	public List<InputAddress> selectDong(String guguncode) {
		// TODO Auto-generated method stub
		return addressMapper.selectDongInput(guguncode);
	}
	
}
