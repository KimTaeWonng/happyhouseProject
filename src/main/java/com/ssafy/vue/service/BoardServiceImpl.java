package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

    @Override
	public List<Board> retrieveBoard() {
		return boardMapper.selectBoard();
	}
    
  	@Override
	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		boardMapper.updateBoardhit(articleno);
		return boardMapper.selectBoardByNo(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	@Override
	@Transactional
	public List<Board> searchBoardBySubject(String keyword) {
		// TODO Auto-generated method stub
		return boardMapper.searchBySubject(keyword);
	}

	@Override
	@Transactional
	public List<Board> searchBoardByUser(String keyword) {
		// TODO Auto-generated method stub
		return boardMapper.searchByUser(keyword);
	}

	@Override
	@Transactional
	public List<Board> searchBoardByContent(String keyword) {
		// TODO Auto-generated method stub
		return boardMapper.searchByContent(keyword);
	}

	@Override
	public List<Board> searchBoardBySubCon(String keyword) {
		// TODO Auto-generated method stub
		return boardMapper.searchBySubCon(keyword);
	}
	@Override
	public List<Board> getPopularBoard(){
		return boardMapper.getPopularBoard();
	}
}