package com.ssafy.vue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/utility")
public class UtilityController {
	@GetMapping("/news")
	@ApiOperation(value = "최신 뉴스를 반환한다.", response = Map.class)    
	public ResponseEntity<List<Map<String,String>>> getNews(){
		try {
			//XML 요청 url
			String url = "https://www.mk.co.kr/rss/50300009";

			//XML 파싱
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("item");
			
			List<Map<String,String>> articles = new ArrayList<>(nodes.getLength()*2);
			for (int k = 0; k < nodes.getLength(); k++) {
				Map<String,String> article = new HashMap<>();
                Node node = nodes.item(k);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    article.put("title", getValue("title", element));
                    article.put("link", getValue("link", element));
                    article.put("description", getValue("description", element));
                }
                articles.add(article);
            }
			
			return new ResponseEntity<List<Map<String,String>>>(articles, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String,String>>>(new ArrayList<Map<String,String>>(), HttpStatus.OK);
		}
	}
	
	private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}
