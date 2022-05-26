package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "Authorization");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("Authorization", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userid") String userid, HttpServletRequest request) {
		//logger.debug("userid : {} ", userid);
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId().equals(userid)) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					//	로그인 사용자 정보.
					MemberDto memberDto = memberService.userInfo(userid);
					result.put("userInfo", memberDto);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("정보조회 실패 : {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@PutMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> updateInfo(@PathVariable("userid") String userid, @RequestBody MemberDto modifiedMember, HttpServletRequest request) {
		//logger.debug("userid : {} ", userid);
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId().equals(userid)) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					//	로그인 사용자 정보.
					memberService.updateMember(modifiedMember);
					result.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("회원 정보 업데이트 실패: {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@DeleteMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> deleteInfo(@PathVariable("userid") String userid,
			HttpServletRequest request) {
		//logger.debug("userid : {} ", userid);
		Map<String, Object> result = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			if(jwtService.getUserId().equals(userid)) {
				// 유효한 토큰에 자기 정보 요청 맞을경우
				try {
					//	로그인 사용자 정보.
					memberService.deleteMember(userid);
					result.put("message", SUCCESS);
					result.put("Authorization", null);
					status = HttpStatus.ACCEPTED;
				} catch (Exception e) {
					logger.error("회원 정보 삭제 실패: {}", e);
					result.put("message", e.getMessage());
					status = HttpStatus.ACCEPTED;
				}
			}else {
				// 토큰 정보랑 불일치 할 경우
				result.put("message", FAIL);
			}
		
		}else {
			// 토근 자체가 유효하지 않음
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}
	
	@GetMapping("/idcheck/{userid}")
	public ResponseEntity<Map<String,Object>> checkID(@PathVariable("userid") String userid) {
		Map<String,Object> result = new HashMap<>();
		try {
			if(memberService.userInfo(userid) == null) {
				result.put("result", true);
			}else {
				result.put("result", false);
			}
		} catch (Exception e) {
			result.put("result", "Internal Error");
		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/valid")
	public ResponseEntity<Map<String,Object>> tokenValidation(HttpServletRequest request) {
		logger.info("tokenValidation");
		Map<String, Object> result = new HashMap<>();
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			result.put("message", SUCCESS);
		} else {
			result.put("Authorization", null);
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) throws Exception {
		logger.debug("logout - 호출");
		Map<String,Object> result = new HashMap<>();
		
		if (jwtService.isUsable(request.getHeader("Authorization"))) {
			result.put("Authorization", null);
			result.put("message", SUCCESS);
		} else {
			result.put("message", FAIL);
		}
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> regist(@RequestBody MemberDto memberDto) throws Exception {
		logger.debug("writeBoard - 호출");
		Map<String,Object> result = new HashMap<>();
		try {
			memberService.regist(memberDto);
			String token = jwtService.create("userid", memberDto.getUserid(), "Authorization");// key, data, subject
			result.put("Authorization", token);
			result.put("message", SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("message", FAIL);
		}
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
