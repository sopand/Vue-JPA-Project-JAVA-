package com.project.team.util;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.project.team.entity.Member;
import com.project.team.entity.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginResolver implements HandlerMethodArgumentResolver{
	private final MemberRepository memberRepo;
	@Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Member.class) &&
                parameter.hasParameterAnnotation(LoginCheck.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	HttpSession session = webRequest.getNativeRequest(HttpServletRequest.class).getSession(false);
        if (session == null || session.getAttribute("member_sid") == null) {
            throw new RuntimeException("로그인 후 이용하세요");
        }
        
        Long memberSid=(Long)session.getAttribute("member_sid");
        
        return memberRepo.findById(memberSid).get();
    }
}
