package com.project.team.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.team.entity.Board;
import com.project.team.entity.BoardRepository;
import com.project.team.entity.Member;
import com.project.team.entity.MemberRepository;
import com.project.team.exception.CustomException;
import com.project.team.exception.ErrorCode;
import com.project.team.req.board.ReqBoardInsert;
import com.project.team.req.board.ReqImageUpload;
import com.project.team.res.ResResult;
import com.project.team.util.FlagYN;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepo;

	private final MemberRepository memberRepo;

	@Value("${upload.directory}")
	private String UPLOAD_DIR;

	@Transactional
	public String imageUpload(ReqImageUpload reqData) throws IOException {
		String fileName = UUID.randomUUID().toString() + "_" + reqData.getUploadFile().getOriginalFilename();
		Path filePath = Path.of(UPLOAD_DIR + fileName);
		Files.copy(reqData.getUploadFile().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		String imageUrl = "/api/image/" + fileName;

		return imageUrl;
	}

	public ResResult boardInsert(ReqBoardInsert reqData, Member member) {
		
		Board newBoard = Board.builder().category(reqData.getCategory()).title(reqData.getTitle())
				.content(reqData.getContent()).member(member).delYn(FlagYN.N).build();
		boardRepo.save(newBoard);

		if (newBoard.getBoardSid() == null) {
			throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
		}
		return ResResult.builder().success(true).message("게시물 등록 성공").build();

	}

}
