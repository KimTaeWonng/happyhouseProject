package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.HouseInfo;

public interface HouseInfoMapper {
	public List<HouseInfo> selectAll();
	public List<HouseInfo> selectBySido(String sido);
	public List<HouseInfo> selectByGugun(String Gugun);
	public List<HouseInfo> selectByDong(String dong);
	public HouseInfo selectByid(String aptCode);
}
