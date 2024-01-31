package com.project.team.req.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqModifyBoard {
	
	@NotBlank(message="수정할 게시글을 선택해주세요")
	@Schema(description = "수정하려는 게시글의 시퀀스")
	private Long boardSid;
	
	@NotBlank(message="제목을 입력해주세요")
	@Schema(description = "제목")
	private String title;
	
	@NotBlank(message = "내용을 입력해주세요")
	@Schema(description = "내용")
	private String content;
}
