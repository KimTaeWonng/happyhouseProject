package com.ssafy.vue.service;

import java.util.List;
import com.ssafy.vue.dto.HouseInfo;

public interface HouseInfoService {
	public List<HouseInfo> selectAll();
	public List<HouseInfo> selectBySido(String sido);
	public List<HouseInfo> selectByGugun(String gun);
	public List<HouseInfo> selectByDong(String dong);
	public HouseInfo selectByid(String aptCode);
}
