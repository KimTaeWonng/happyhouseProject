package com.ssafy.vue.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.springframework.web.bind.annotation.RestController;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.ssafy.vue.dto.InputAddress;
import com.ssafy.vue.service.AddressService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/address")
public class AddressController {
	private static final String APIKEY = "S9wVuq5E8hXQYl7J9G+jbzEHH04njqCCPE+hx1zbOy8";
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<InputAddress>> selectSido() throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectSido(), HttpStatus.OK);
	}
	
	@GetMapping("/gugun/{sidocode}")
	public ResponseEntity<List<InputAddress>> selectGugun(@PathVariable String sidocode) throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectGugun(sidocode), HttpStatus.OK);
	}
	
	@GetMapping("/dong/{guguncode}")
	public ResponseEntity<List<InputAddress>> selectDong(@PathVariable String guguncode) throws Exception {
		logger.debug("selectSido - 호출");
		return new ResponseEntity<List<InputAddress>>(addressService.selectDong(guguncode), HttpStatus.OK);
	}

	@PostMapping("/path")
	public ResponseEntity<String> searchPath(@RequestBody Map<String,String> payload) throws Exception {
		payload.put("apiKey", APIKEY);
		
		URI uri = new URI("https://api.odsay.com/v1/api/searchPubTransPathT");
		HttpClient http = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(uri);
		
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		for(Entry<String,String> iter : payload.entrySet()) {
			param.add(new BasicNameValuePair(iter.getKey(), iter.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(param));
		
		HttpResponse res = http.execute(post);
        if(res.getStatusLine().getStatusCode() == 200) {
        	ResponseHandler<String> vResponseHandler = new BasicResponseHandler();
        	String vResponse = vResponseHandler.handleResponse(res);
        	
        	return new ResponseEntity<String>(vResponse, HttpStatus.OK);
        }
        
        return new ResponseEntity<String>("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
