package com.ssafy.vue.service;

import java.util.List;
import com.ssafy.vue.dto.Comment;

public interface CommentService {
	public List<Comment> boardComment(int articleno);
}
