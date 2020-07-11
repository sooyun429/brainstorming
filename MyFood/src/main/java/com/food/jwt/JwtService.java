package com.food.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.food.account.AccountVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	/*
	 *  로그인 성공시 사용자 정보를 기반으로  JWTTOken을 생성해서 반환
	 */
	public String create(final AccountVO account) {
		
		JwtBuilder builder = Jwts.builder(); 
		// JWT TOKEN = HEADER + PAYLOAD + SIGNAGURE
		
		
		// HEADER 설정
		builder.setHeaderParam("typ", "JWT");
		
		// PAYLOAD 설정
		builder.setSubject("payload")
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
				.claim("User", account);
		
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		// 직렬화 처리
		String jwt = builder.compact();
		
		return jwt;

	}
	
	public boolean CheckValid(String jwt) {
		
		 Claims claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody();
		 
		 if ((Integer)claims.get("exp") > 0) {
			 return true;
		 }
		 return false;
	}
	
	public String CheckUserNickname(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody();
		
		@SuppressWarnings("unchecked")
		String result = (String) ((Map<String, Object>)claims.get("User")).get("user_nickname");
		return result != null ? result: "";
	}
	
	public Map<String, Object> get(String jwt) {
		Jwt<Header, Claims> claims;
		
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJwt(jwt);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return claims.getBody();
	}
}
