package com.project.team.res.comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResSelectComment {
	
	
	@Schema(description = "현재 페이지")
	private int nowPage;
	
	@Schema(description = "시작 페이지")
	private int startPage;
	
	@Schema(description = "마지막 페이지")
	private int endPage;
	
	@Schema(description = "댓글 목록")
	private List<DTO> commentList = new ArrayList<>();

	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	@Builder
	@Getter
	@Schema(name = "ResSelectBoardInnerDTO")
	public static class DTO {

		@Schema(description = "댓글 시퀀스아이디")
		private Long comment_sid;

		@Schema(description = "댓글 내용")
		private String content;

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
