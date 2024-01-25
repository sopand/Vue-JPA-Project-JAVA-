package com.project.team.controller;

import java.io.IOException;
import java.nio.Buffer;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.team.req.board.ReqImageUpload;
import com.project.team.res.ResResult;
import com.project.team.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Tag(name="게시글 API",description ="게시글 API")
public class BoardController {
	
	private final BoardService boardSRV;
	
	@PostMapping(value="/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResResult> imageUpload(@ModelAttribute ReqImageUpload reqData) throws IOException{
		String url=boardSRV.imageUpload(reqData);
		ResResult result=ResResult.builder()
				.success(true)
				.data(url)
				.message("이미지 업로드 완료")
				.build();
		return ResponseEntity.ok(result);
		
	}

}
