package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.HouseDeal;

public interface HouseDealMapper {
	public List<HouseDeal> selectAll();
	public List<HouseDeal> selectByAptCode(String aptCode);
}
