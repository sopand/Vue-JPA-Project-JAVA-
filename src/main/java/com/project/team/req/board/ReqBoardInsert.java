package com.project.team.req.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqBoardInsert {
	
	@NotNull(message = "제목을 입력해주세요")
	@Schema(description = "제목")
	private String title;
	
	@NotNull(message = "카테고리를 선택해주세요")
	@Schema(description = "카테고리 코드 01:공지 ,02 : 커뮤니티 03 : Q&A")
	private String category;
	
	@NotNull(message = "내용을 입력해주세요")
	@Schema(description = "내용")
	private String content;
	

}
