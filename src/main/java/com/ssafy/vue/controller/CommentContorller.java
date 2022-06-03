package com.ssafy.vue.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;
import com.ssafy.vue.service.CommentService;
import com.ssafy.vue.service.JwtService;
import com.ssafy.vue.service.JwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/comment")
public class CommentContorller {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private JwtService jwtService;

    @ApiOperation(value = "글번호에 해당하는 게시글의 댓글을 반환한다.", response = Board.class)    
   	@GetMapping("{articleno}")
   	public ResponseEntity<Map<String,Object>> boardComment(@PathVariable int articleno, HttpServletRequest request) {
   		logger.debug("boardComment - 호출");
   		Map<String,Object> result = new HashMap<String, Object>();
   		if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   		}
   		try {
   			result.put("message", commentService.boardComment(articleno));
		} catch (Exception e) {
			result.put("message", new ArrayList());
		}
   		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
   	}
    
    @ApiOperation(value = "게시글의 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody Comment comment, HttpServletRequest request) {
    	
    	logger.debug("writeComment - 호출");
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			if(jwtService.getUserId().equals(comment.getUserid())) {
   				try {
   					result.put("commentno", commentService.writeComment(comment));
   					result.put("message", "success");
				} catch (Exception e) {
					e.printStackTrace();
					result.put("message","error");
				}
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
    
    @ApiOperation(value = "게시글의 댓글을 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{commentno}")
	public ResponseEntity<Map<String, Object>> updateComment(@RequestBody Comment comment, HttpServletRequest request) throws SQLException {
    	
    	logger.debug("updateComment - 호출");
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			if(jwtService.getUserId().equals(comment.getUserid())) {
   				result.put("message", commentService.changeComment(comment) ? "success" : "error");
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
    
    @ApiOperation(value = "게시글의 댓글을 삭제한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{commentno}")
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int commentno, HttpServletRequest request) throws SQLException {
    	logger.debug("deleteComment - 호출");
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			if(jwtService.getUserId().equals(commentService.select(commentno).getUserid())) {
   				result.put("message", commentService.deleteComment(commentno) ? "success" : "error");
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
}
