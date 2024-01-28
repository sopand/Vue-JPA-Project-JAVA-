package com.project.team.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResResult {
	
	@Schema(description = "API 요청 성공 여부 true: 성공  , false 실패")
	private Boolean success;
	
	@Schema(description = "API 응답 메시지")
	private String message;
	
	@Schema(description = "API 응답 데이터")
	private Object data;

}
