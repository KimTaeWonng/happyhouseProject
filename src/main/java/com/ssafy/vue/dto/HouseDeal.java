package com.ssafy.vue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseDeal {
	private String no;
	private String dealAmount;
	private String dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private int floor;
	private String aptCode;
	
}
