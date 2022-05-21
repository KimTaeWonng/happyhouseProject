package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.InputAddress;

public interface AddressMapper {
	public List<InputAddress> selectSidoInput();
	public List<InputAddress> selectGugunInput(String sidocode);
	public List<InputAddress> selectDongInput(String guguncode);
}
