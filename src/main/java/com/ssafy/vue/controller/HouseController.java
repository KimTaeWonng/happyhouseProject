package com.ssafy.vue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.Comment;
import com.ssafy.vue.dto.HouseDeal;
import com.ssafy.vue.dto.HouseInfo;
import com.ssafy.vue.service.CommentService;
import com.ssafy.vue.service.HouseDealService;
import com.ssafy.vue.service.HouseInfoService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/house")
public class HouseController {
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	
	@Autowired
	private HouseDealService houseDealService;
	
	@Autowired
	private HouseInfoService houseInfoService;
	
	
	@GetMapping("/houseinfo/getAll")
	@ApiOperation(value = "모든 집 정보를 반환한다. 최대 1000건까지 제공 ", response = HouseInfo.class)    
	public ResponseEntity<List<HouseInfo>> houseInfoSelectAll(){
		logger.debug("모든 집 정보 얻기");
   		return new ResponseEntity<List<HouseInfo>>(houseInfoService.selectAll(), HttpStatus.OK);
	}
	
	
	@GetMapping("/houseinfo/{aptCode}")
	@ApiOperation(value = "해당 아파트의 정보를 반환한다", response = HouseInfo.class)    
	public ResponseEntity<HouseInfo> selectByAptCode(@PathVariable String aptCode){
		logger.debug(aptCode + " 정보 얻기");
   		return new ResponseEntity<HouseInfo>(houseInfoService.selectByid(aptCode), HttpStatus.OK);
	}

	@PostMapping("/houseinfo/getAll")
	@ApiOperation(value = "선택한 지역에 맞는 정보를 반환한다. sido, gugun, dong을 json으로 전달해야됨 ", response = HouseInfo.class)    
	public ResponseEntity<List<HouseInfo>> houseInfoByLocation(@RequestBody Map<String, String> payload){
		String sido = payload.get("sido");
		String gugun = payload.get("gugun");
		String dong = payload.get("dong");
		
		List<HouseInfo> result;
		
		if(dong != null) {
			result = houseInfoService.selectByDong(dong);
		}else if(gugun != null) {
			result = houseInfoService.selectByGugun(gugun);
		}else if(sido != null) {
			result = houseInfoService.selectBySido(sido);
		}else {
			return new ResponseEntity<List<HouseInfo>>(new ArrayList<HouseInfo>(), HttpStatus.BAD_REQUEST);
		}
   		return new ResponseEntity<List<HouseInfo>>(result, HttpStatus.OK);
	}

	@GetMapping("/housedeal/getAll")
	@ApiOperation(value = "모든 집 거래 정보를 반환한다.", response = HouseDeal.class)    
	public ResponseEntity<List<HouseDeal>> houseDealSelectAll(){
   		return new ResponseEntity<List<HouseDeal>>(houseDealService.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping("/housedeal/{aptCode}")
	@ApiOperation(value = "해당 아파트의 거래정보를 반환한다.", response = HouseDeal.class)    
	public ResponseEntity<List<HouseDeal>> houseDealSelectAll(@PathVariable String aptCode){
   		return new ResponseEntity<List<HouseDeal>>(houseDealService.selectAll(aptCode), HttpStatus.OK);
	}
	
}
