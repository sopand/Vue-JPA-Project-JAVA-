package com.project.team.req.member;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqMemberJoin {
	
	
	@Schema(description = "이메일 최대 30자까지",requiredMode =RequiredMode.REQUIRED)
	private String email;
	
	@Schema(description = "비밀번호 ",requiredMode =RequiredMode.REQUIRED)
	private String password;
	
	@Schema(description = "사용자 이름 or 별명",requiredMode = RequiredMode.REQUIRED)
	private String name;

}
