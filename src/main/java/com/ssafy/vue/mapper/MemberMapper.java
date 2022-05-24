package com.ssafy.vue.mapper;

import java.sql.SQLException;

import com.ssafy.vue.dto.MemberDto;


public interface MemberMapper {

	public int regist(MemberDto memberDto) throws SQLException;
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public int updateMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String userid) throws SQLException;
}