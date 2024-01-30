package com.project.team.res.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResSelectBoard {
	
	@Schema(description = "현재 페이지")
	private int nowPage;
	
	@Schema(description = "시작 페이지")
	private int startPage;
	
	@Schema(description = "마지막 페이지")
	private int endPage;
	
	@Schema(description = "게시글 목록")
	private List<DTO> boardList = new ArrayList<>();

	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@Builder
	@Getter
	@Schema(name = "ResSelectBoardInnerDTO")
	public static class DTO {

		@Schema(description = "게시글 시퀀스아이디")
		private Long boardSid;

		@Schema(description = "게시글 제목")
		private String title;


		@Schema(description = "작성자 명")
		private String memberName;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@Schema(description = "작성 일자")
		private LocalDateTime createDate;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@Schema(description = "수정 일자")
		private LocalDateTime updateDate;

	}

}
