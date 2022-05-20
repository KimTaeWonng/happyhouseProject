package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> boardComment(int articleno);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int commentno);
}
