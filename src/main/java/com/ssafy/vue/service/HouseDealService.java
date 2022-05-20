package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.mapper.HouseDealMapper;

public interface HouseDealService {
	public List<HouseDeal> selectAll();
	public List<HouseDeal> selectAll(String aptCode);
}
