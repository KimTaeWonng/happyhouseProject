package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "House (주택 거래 정보)", description = "작성자아이디, 내용, 작성일")
public class HouseDeal {
	@ApiModelProperty(value = "거래 번호")
	private String no;
	@ApiModelProperty(value = "거래 금액")
	private String dealAmount;
	@ApiModelProperty(value = "거래 년도")
	private String dealYear;
	@ApiModelProperty(value = "거래 월")
	private int dealMonth;
	@ApiModelProperty(value = "거래 날짜")
	private int dealDay;
	@ApiModelProperty(value = "평수")
	private String area;
	@ApiModelProperty(value = "층")
	private int floor;
	@ApiModelProperty(value = "아파트 코드(foreign key)")
	private String aptCode;
	
}
