package com.ssafy.vue.util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.expmin}")
	private Long expireMin;

	public String createAuthToken(String id) {
		return create(id, "authToken", expireMin);
	}

	private String create(String id, String subject, long expireMin) {
		final JwtBuilder jb = Jwts.builder();
		
		jb.setSubject(subject).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin)); // 2시간 이후 만료됨
		
		if(id!=null) {
			jb.claim("user", id);
		}
		
		jb.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		final String jwt = jb.compact();
		log.debug("토큰 발행: {}", jwt);
		return jwt;
	}

	public Map<String, Object> checkAndGetClaims(String jwt) {
		Jws<Claims> claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		log.trace("claims: {}",claims);
		
		return claims.getBody();
	}
}
