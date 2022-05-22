package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.Board;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public boolean writeBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
	public List<Board> searchBoardBySubject(String keyword);
	public List<Board> searchBoardByUser(String keyword);
	public List<Board> searchBoardByContent(String keyword);
	public List<Board> searchBoardBySubCon(String keyword);
	public List<Board> getPopularBoard();
}
