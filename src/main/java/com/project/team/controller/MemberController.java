package com.project.team.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.team.req.member.ReqMemberJoin;
import com.project.team.req.member.ReqMemberLogin;
import com.project.team.res.ResResult;
import com.project.team.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@Tag(name="유저 API",description ="유저 데이터 조작 API")
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberSRV;
	
	@PostMapping("/join")
	@Operation(summary = "회원 가입하기")
	@ApiResponse(responseCode = "200",description = "유저 회원가입 성공")
	@ApiResponse(responseCode = "400",description = "유저 회원가입 실패")
	public ResponseEntity<ResResult> memberJoin(@RequestBody ReqMemberJoin reqData){
		ResResult result=memberSRV.memberJoin(reqData);
		
		if(!result.getSuccess()) {
			return ResponseEntity.status(400).body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/login")
	@Operation(summary = "회원 로그인")
	@ApiResponse(responseCode = "200",description = "유저 로그인 성공")
	@ApiResponse(responseCode = "400",description = "유저 로그인 실패")
	public ResponseEntity<ResResult> memberLogin(@RequestBody ReqMemberLogin reqData,HttpServletRequest req){
		ResResult result=memberSRV.memberLogin(reqData);
		if(!result.getSuccess()) {
			return ResponseEntity.status(400).body(result);
		}
		HttpSession session=req.getSession();
		session.setAttribute("member_sid",(Long)result.getData());
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/logout")
	@Operation(summary = "회원 로그아웃")
	@ApiResponse(responseCode = "200" , description = "로그아웃 완료")
	@ApiResponse(responseCode = "400" , description = "로그아웃 실패")
	public ResponseEntity<ResResult> memberLogout(HttpServletRequest req){
		HttpSession session=req.getSession(false);
		
		if(session!=null) {
			session.removeAttribute("member_sid");
			session.invalidate();
		}
		
		return ResponseEntity.ok(ResResult.builder().success(true).message("로그아웃이 완료되었습니다").build()
				);
	}
	
	
	
	
	
	
	
	

}
