package com.project.team.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.team.entity.Board;
import com.project.team.entity.BoardRepository;
import com.project.team.req.board.ReqBoardInsert;
import com.project.team.req.board.ReqImageUpload;
import com.project.team.res.ResResult;
import com.project.team.util.BoardCategory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepo;
	
	@Value("${upload.directory}")
	private String UPLOAD_DIR;
	
	public String imageUpload(ReqImageUpload reqData) throws IOException {
		 String fileName = UUID.randomUUID().toString() + "_" + reqData.getUploadFile().getOriginalFilename();
         Path filePath = Path.of(UPLOAD_DIR + fileName);
         Files.copy(reqData.getUploadFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
         String imageUrl = "/api/image/" + fileName;
         
         return imageUrl;
	}
	
	public ResResult boardInsert(ReqBoardInsert reqData) {
			
		Board newBoard=Board.builder()
				.category(BoardCategory.of(reqData.getCategory()))
				.title(reqData.getTitle())
				.content(reqData.getContent())
				.build();
		boardRepo.save(newBoard);
		
		return ResResult.builder()
				.success(true)
				.message("게시물 등록 성공")
				.build();
		
	}
	

}
