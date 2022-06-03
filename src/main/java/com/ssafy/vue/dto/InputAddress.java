package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Address (주택 거래 정보)", description = "시도,구군,동 코드,이름")
public class InputAddress {
	
	private String name;
	private String code;
	
}
