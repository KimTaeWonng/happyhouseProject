package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;

public interface CommentService {
	public List<Comment> boardComment(int articleno) throws SQLException;
	public Comment select(int commentno) throws SQLException;
	public int writeComment(Comment comment) throws SQLException;
	public boolean changeComment(Comment comment) throws SQLException;
	public boolean deleteComment(int commentno) throws SQLException;
}
