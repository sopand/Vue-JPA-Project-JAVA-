package com.project.team.req.board;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReqImageUpload {
	
	@Schema(description = "업로드할 파일")
	private MultipartFile uploadFile;
		
}
