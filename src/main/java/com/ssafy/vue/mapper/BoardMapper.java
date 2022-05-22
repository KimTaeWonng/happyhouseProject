package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.Board;
@Mapper
public interface BoardMapper {
	public List<Board> selectBoard();
	public Board selectBoardByNo(int articleno);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int articleno);
	public List<Board> searchBySubject(String keyword);
	public List<Board> searchByUser(String keyword);
	public List<Board> searchByContent(String keyword);
	public List<Board> searchBySubCon(String keyword);
	public int updateBoardhit(int articleno);
	public List<Board> getPopularBoard();
}