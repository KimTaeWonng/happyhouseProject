package com.ssafy.vue.controller;

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
import com.ssafy.vue.service.BoardService;
import com.ssafy.vue.service.CommentService;
import com.ssafy.vue.service.JwtService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;	
	
	@Autowired
	private JwtService jwtService;

	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}
    
	@GetMapping("search/sub/{keyword}")
	public ResponseEntity<List<Board>> searchBoardBySubject(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardBySubject(keyword), HttpStatus.OK);
	}
    
	@GetMapping("search/user/{keyword}")
	public ResponseEntity<List<Board>> searchBoardByUser(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardByUser(keyword), HttpStatus.OK);
	}
    
	@GetMapping("search/con/{keyword}")
	public ResponseEntity<List<Board>> searchBoardByContent(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardByContent(keyword), HttpStatus.OK);
	}
    
	@GetMapping("search/subcon/{keyword}")
	public ResponseEntity<List<Board>> searchBoardBySubCon(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardBySubCon(keyword), HttpStatus.OK);
	}

	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(boardService.detailBoard(articleno), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String,Object>> writeBoard(@RequestBody Board board, HttpServletRequest request) {
    	logger.debug("writeBoard - 호출");
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			if(jwtService.getUserId().equals(board.getUserid())) {
   				result.put("message",boardService.writeBoard(board) ? "success" : "error");
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}

	@PutMapping("{articleno}")
	public ResponseEntity<Map<String,Object>> updateBoard(@RequestBody Board board, HttpServletRequest request) {
    	
    	logger.debug("updateBoard - 호출");
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			if(jwtService.getUserId().equals(board.getUserid())) {
   				result.put("message",boardService.updateBoard(board) ? "success" : "error");
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}

	@DeleteMapping("{articleno}")
	public ResponseEntity<Map<String,Object>> deleteBoard(@PathVariable int articleno, HttpServletRequest request) {
		logger.debug("deleteBoard - 호출");
		
    	Map<String,Object> result = new HashMap<String, Object>();
    	if (!jwtService.isUsable(request.getHeader("Authorization"))){
   			result.put("Authorization", null);
   			result.put("message","error");
   		}else {
   			
   			if(jwtService.getUserId().equals(boardService.detailBoard(articleno).getUserid())) {
   				result.put("message",boardService.deleteBoard(articleno) ? "success" : "error");
   			}else {
   				result.put("message","error");
   			}
   		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}  
    
	@GetMapping("board/popular")
	public ResponseEntity<List<Board>> getPopularBoard() throws Exception {
		logger.debug("getPopularBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.getPopularBoard(), HttpStatus.OK);
	}
}