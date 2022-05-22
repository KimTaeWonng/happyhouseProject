package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseInfo;
import com.ssafy.vue.mapper.HouseInfoMapper;

@Service
public class HouseInfoServiceImpl implements HouseInfoService {

	@Autowired
	HouseInfoMapper mapper;
	
	@Override
	public List<HouseInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<HouseInfo> selectBySido(String sido) {
		return mapper.selectBySido(sido);
	}

	@Override
	public List<HouseInfo> selectByGugun(String gun) {
		return mapper.selectByGugun(gun);
	}

	@Override
	public List<HouseInfo> selectByDong(String dong) {
		return mapper.selectByDong(dong);
	}
	
	@Override
	public HouseInfo selectByid(String aptCode) {
		return mapper.selectByid(aptCode);
	}

}
