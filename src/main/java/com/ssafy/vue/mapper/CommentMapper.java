package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> boardComment(int articleno) throws SQLException;
	public Comment select(int commentno) throws SQLException;
	public int insertComment(Comment comment) throws SQLException;
	public int changeComment(Comment comment) throws SQLException;
	public int deleteComment(int commentno) throws SQLException;
}
