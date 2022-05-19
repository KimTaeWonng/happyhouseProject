package com.ssafy.vue.controller;

import java.util.List;

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

import io.swagger.annotations.ApiOperation;

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
	private CommentService commentService;

    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "해당 검색어를 포함한 제목의 모든 게시글을 반환한다.", response = List.class)
	@GetMapping("search/sub/{keyword}")
	public ResponseEntity<List<Board>> searchBoardBySubject(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardBySubject(keyword), HttpStatus.OK);
	}
    
    @ApiOperation(value = "해당 검색어를 포함한 유저의 모든 게시글을 반환한다.", response = List.class)
	@GetMapping("search/user/{keyword}")
	public ResponseEntity<List<Board>> searchBoardByUser(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardByUser(keyword), HttpStatus.OK);
	}
    
    @ApiOperation(value = "해당 검색어를 포함한 내용의 모든 게시글을 반환한다.", response = List.class)
	@GetMapping("search/con/{keyword}")
	public ResponseEntity<List<Board>> searchBoardByContent(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardByContent(keyword), HttpStatus.OK);
	}
    
    @ApiOperation(value = "해당 검색어를 포함한 내용 또는 제목의 모든 게시글을 반환한다.", response = List.class)
	@GetMapping("search/subcon/{keyword}")
	public ResponseEntity<List<Board>> searchBoardBySubCon(@PathVariable String keyword) throws Exception {
		logger.debug("searchBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.searchBoardBySubCon(keyword), HttpStatus.OK);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)    
	@GetMapping("{articleno}")
	public ResponseEntity<Board> detailBoard(@PathVariable int articleno) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(boardService.detailBoard(articleno), HttpStatus.OK);
	}
    
    @ApiOperation(value = "글번호에 해당하는 게시글의 댓글을 반환한다.", response = Board.class)    
   	@GetMapping("comment/{articleno}")
   	public ResponseEntity<List<Comment>> boardComment(@PathVariable int articleno) {
   		logger.debug("boardComment - 호출");
   		
   		return new ResponseEntity<List<Comment>>(commentService.boardComment(articleno), HttpStatus.OK);
   	}

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board) {
		logger.debug("writeBoard - 호출");
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int articleno) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}