package com.ssafy.vue.service;

import com.ssafy.vue.dto.MemberDto;

public interface MemberService {
	
	
	public boolean regist(MemberDto memberDto) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	
}
