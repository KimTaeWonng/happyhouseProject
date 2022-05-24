package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Comment;
import com.ssafy.vue.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper mapper;
	
	@Override
	public List<Comment> boardComment(int articleno) {
		return mapper.boardComment(articleno);
	}

	@Override
	@Transactional
	public boolean writeComment(Comment comment) {
		// TODO Auto-generated method stub
		return mapper.insertComment(comment) == 1;
	}

	@Override
	@Transactional
	public boolean deleteComment(int commentno) {
		// TODO Auto-generated method stub
		return mapper.deleteComment(commentno) == 1;
	}

	@Override
	@Transactional
	public boolean changeComment(Comment comment) {
		return mapper.changeComment(comment) == 1;
		
	}

	@Override
	public Comment select(int commentno) {
		return mapper.select(commentno);
	}

}
