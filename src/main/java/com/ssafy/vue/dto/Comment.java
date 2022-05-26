package com.ssafy.vue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private int commentno;
	private int articleno;
	private String userid;
	private String content;
	private String regtime;
}
