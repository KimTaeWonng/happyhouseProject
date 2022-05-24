package com.ssafy.vue.service;

import java.sql.SQLException;
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
	public List<Comment> boardComment(int articleno) throws SQLException{
		return mapper.boardComment(articleno);
	}

	@Override
	@Transactional
	public int writeComment(Comment comment) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.insertComment(comment);
	}

	@Override
	@Transactional
	public boolean deleteComment(int commentno) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.deleteComment(commentno) == 1;
	}

	@Override
	@Transactional
	public boolean changeComment(Comment comment) throws SQLException {
		return mapper.changeComment(comment) == 1;
		
	}

	@Override
	public Comment select(int commentno) throws SQLException {
		return mapper.select(commentno);
	}

}
