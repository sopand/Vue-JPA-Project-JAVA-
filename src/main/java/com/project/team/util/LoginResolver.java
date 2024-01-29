package com.project.team.util;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.webjars.NotFoundException;

import com.project.team.entity.Member;
import com.project.team.entity.MemberRepository;
import com.project.team.exception.CustomException;
import com.project.team.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginResolver implements HandlerMethodArgumentResolver {
	
	private final MemberRepository memberRepo;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(LoginCheck.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = httpServletRequest.getSession(false);
		if (session == null || session.getAttribute("member_sid") == null) { // null || member_sid ==null Login 에러작동
			throw new CustomException(ErrorCode.NOT_LOGIN_USER);
		}
		
		Long memberSid = (Long) session.getAttribute("member_sid");
		Member findMember = memberRepo.findById(memberSid).orElse(null);
		
		if (findMember == null) { // 유저정보가 없을경우 작동
			throw new CustomException(ErrorCode.SESSION_USER_NOT_FOUND);
		}
		
		return findMember;
	}
}
