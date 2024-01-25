package com.project.team.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.team.entity.Member;
import com.project.team.entity.MemberRepository;
import com.project.team.req.member.ReqMemberJoin;
import com.project.team.req.member.ReqMemberLogin;
import com.project.team.res.ResResult;
import com.project.team.util.FlagYN;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepo;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public ResResult memberJoin(ReqMemberJoin reqData) {
		Member existingMember = memberRepo.findByEmail(reqData.getEmail()).orElse(null);
		if(existingMember!=null) {
			return ResResult.builder().success(false).message("기존에 존재하는 이메일입니다.").build();
		}
		Member joinData = Member.builder()
				.email(reqData.getEmail())
				.password(passwordEncoder.encode(reqData.getPassword()))
				.name(reqData.getName()).delYn(FlagYN.N)
				.build();
		
		Member saveMember = memberRepo.save(joinData);
		if (saveMember == null) {
			return ResResult.builder().success(false).message("회원가입에 실패하였습니다").build();
		}
		return ResResult.builder().success(true).message(reqData.getName() + " 님 회원가입이 완료되었습니다.")
				.data(saveMember.getMemberSid()).build();
	}

	public ResResult memberLogin(ReqMemberLogin reqData) {
		
		Member member = memberRepo.findByEmail(reqData.getEmail()).orElse(null);
		
		if(member==null) {
			return ResResult.builder().success(false).message("해당 회원이 존재하지 않습니다").build();
		}
		if(!passwordEncoder.matches(reqData.getPassword(),member.getPassword())) {
			return ResResult.builder().success(false).message("비밀번호가 일치하지 않습니다").build();
		}
		return ResResult.builder().success(true).message("로그인에 성공하였습니다.").data(member.getMemberSid()).build();
	}

	

}
