package com.project.team.req.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqInsertComment {
	
	@NotBlank(message = "내용을 입력해주세요")
	@Schema(description = "댓글의 내용")
	private String content;
	
	
	@NotNull(message = "댓글이 추가될 게시글이 선택되지 않았습니다.")
	@Schema(description = "댓글 등록할 게시글 시퀀스")
	private Long boardSid;
}
