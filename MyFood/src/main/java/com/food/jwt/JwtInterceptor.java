package com.food.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if(request.getMethod().equals("OPTIONS")) {
			return true;
		} else {
			
			String token = request.getHeader("jwt-auth-token");
			if(token != null & token.length() > 0) {
				boolean jwtCheckExp = jwtService.CheckValid(token);
				if (jwtCheckExp == false) return false;
				return true;
			}
			else {
				throw new RuntimeException("인증토큰이 없습니다");
			}
		}
		
	}

}
