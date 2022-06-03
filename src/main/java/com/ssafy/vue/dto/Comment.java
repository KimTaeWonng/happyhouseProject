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
@ApiModel(value = "Comment (댓글정보)", description = "작성자아이디, 내용, 작성일")
public class Comment {
	@ApiModelProperty(value = "댓글 번호")
	private int commentno;
	@ApiModelProperty(value = "게시글 번호")
	private int articleno;
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
}
