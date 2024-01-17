package com.project.team.res.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResMemberList {
	
	@Schema(description = "유저 아이디")
	public String email;
	
	@Schema(description = "유저 별명")
	public String name;
}
