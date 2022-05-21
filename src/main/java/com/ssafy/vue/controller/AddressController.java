package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Board;
import com.ssafy.vue.dto.InputAddress;
import com.ssafy.vue.service.AddressService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/address")
public class AddressController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private AddressService addressService;
	
	@ApiOperation(value = "시,도 이름 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<InputAddress>> selectSido() throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "시,도에 존재하는 구,군 이름 반환", response = List.class)
	@GetMapping("{sidocode}")
	public ResponseEntity<List<InputAddress>> selectGugun(@PathVariable String sidocode) throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectGugun(sidocode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "시,도 이름 반환", response = List.class)
	@GetMapping("{guguncode}")
	public ResponseEntity<List<InputAddress>> selectDong(@PathVariable String guguncode) throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectDong(guguncode), HttpStatus.OK);
	}

}
