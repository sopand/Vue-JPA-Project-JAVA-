package com.project.team.util;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof ResourceHttpRequestHandler) {
			// 정적 리소스에 대한 요청이면 인터셉터를 실행하지 않음
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
		if (loginCheck == null) {
			return true;
		}
		HttpSession session = request.getSession(false);
		if (session == null) {
			throw new Exception("로그인 후 이용하세요");
		}
		return true;
	}

}
