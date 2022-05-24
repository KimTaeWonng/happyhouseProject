package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;

public interface CommentService {
	public List<Comment> boardComment(int articleno);
	public boolean writeComment(Comment comment);
	public boolean changeComment(Comment comment);
	public boolean deleteComment(int commentno);
}
