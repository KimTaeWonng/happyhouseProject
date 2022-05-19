package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> boardComment(int articleno);
}
