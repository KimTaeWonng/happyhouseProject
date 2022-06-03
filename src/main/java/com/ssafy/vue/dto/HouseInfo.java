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
@ApiModel(value = "House (주택 정보)", description = "주택 정보")
public class HouseInfo {
	@ApiModelProperty(value = "아파트 코드")
	private String aptCode;
	@ApiModelProperty(value = "건설 년도")
	private int buildYear;
	@ApiModelProperty(value = "도로명")
	private String roadName;
	@ApiModelProperty(value = "도로명 본번")
	private int roadNameBonbun;
	@ApiModelProperty(value = "도로명 부번")
	private int roadNameBubun;
	@ApiModelProperty(value = "동")
	private String dong;
	@ApiModelProperty(value = "동 코드")
	private String dongCode;
	@ApiModelProperty(value = "아파트 명")
	private String apartmentName;
	@ApiModelProperty(value = "지번")
	private String jibun;
	@ApiModelProperty(value = "위도")
	private float lat;
	@ApiModelProperty(value = "경도")
	private float lng;
	@ApiModelProperty(value = "거래 내역 갯수")
	private String dealCount;
}
