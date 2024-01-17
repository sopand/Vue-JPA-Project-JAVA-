package com.project.team.req.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqMemberLogin {
	
	@Schema(description = "유저의 아이디")
	private String email;
	
	@Schema(description = "비밀번호 ")
	private String password;
}
