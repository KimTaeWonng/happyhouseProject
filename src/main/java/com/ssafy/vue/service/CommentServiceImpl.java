package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
