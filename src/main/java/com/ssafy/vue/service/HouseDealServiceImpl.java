package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.mapper.HouseDealMapper;


@Service
public class HouseDealServiceImpl implements HouseDealService {

	@Autowired
	private HouseDealMapper mapper;
	
	@Override
	public List<HouseDeal> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<HouseDeal> selectAll(String aptCode) {
		return mapper.selectByAptCode(aptCode);
	}

}
