package com.project.team.req.board;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqBoardInsert {
	
	@Schema(description = "제목")
	private String title;
	
	@Schema(description = "카테고리 코드 01:공지 ,02 : 커뮤니티 03 : Q&A")
	private String category;
	
	@Schema(description = "내용")
	private String content;
	

}
