package com.project.team.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.project.team.entity.Board;
import com.project.team.entity.BoardRepository;
import com.project.team.entity.Member;
import com.project.team.entity.MemberRepository;
import com.project.team.exception.CustomException;
import com.project.team.exception.ErrorCode;
import com.project.team.req.board.ReqBoardInsert;
import com.project.team.res.ResResult;
import com.project.team.util.FlagYN;
import com.project.team.util.Utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepo;

	private final MemberRepository memberRepo;

	private final UploadService upload;

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		return objectMetadata;
	}

	private String generateFileName(MultipartFile file) {
		return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
	}

//	@Transactional
//	public String imageUpload(ReqImageUpload reqData) throws IOException {
//		String bucketDir = bucketName + UPLOAD_DIR;
//		String dirUrl = bucketName + UPLOAD_DIR + "/";
//		String fileName = generateFileName(reqData.getUploadFile());
//		
//		amazonS3.putObject(bucketDir, fileName, reqData.getUploadFile().getInputStream(), getObjectMetadata(reqData.getUploadFile()));
//		return UPLOAD_URL+"/"+dirUrl + fileName;
//		
//		
//	}
	
	@Transactional
	public ResResult boardInsert(ReqBoardInsert reqData, Member member) throws Exception {

		Board newBoard = Board.builder().category(reqData.getCategory()).title(reqData.getTitle()).member(member)
				.delYn(FlagYN.N).build();
		boardRepo.save(newBoard);

		if (newBoard.getBoardSid() == null) {
			throw new CustomException(ErrorCode.DATA_INSERT_FAILED);
		}

		String content = Utils.editorImageUpload(upload, reqData.getContent(), newBoard);

		newBoard.contentImage(content);
		return ResResult.builder().success(true).message("게시물 등록 성공").build();

	}

}
