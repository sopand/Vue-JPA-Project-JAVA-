package com.project.team.res.board;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResDetailBoard {
	
	@Schema(description = "게시글 시퀀스아이디")
	private Long boardSid;

	@Schema(description = "게시글 제목")
	private String title;
	
	@Schema(description = "게시글 내용")
	private String content;


	@Schema(description = "작성자 명")
	private String memberName;
	
	@Schema(description = "작성자 시퀀스")
	private Long memberSid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "작성 일자")
	private LocalDateTime createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Schema(description = "수정 일자")
	private LocalDateTime updateDate;
	
}
