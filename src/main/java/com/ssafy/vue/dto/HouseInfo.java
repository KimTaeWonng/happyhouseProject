package com.ssafy.vue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseInfo {
	private String aptCode;
	private int buildYear;
	private String roadName;
	private int roadNameBonbun;
	private int roadNameBubun;
	private String dong;
	private String dongCode;
	private String apartmentName;
	private String jibun;
	private float lat;
	private float lng;
	private String dealCount;
}
