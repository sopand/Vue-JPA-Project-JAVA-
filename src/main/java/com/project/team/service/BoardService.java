package com.project.team.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
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

	private final AmazonS3Client amazonS3;

	private final MemberRepository memberRepo;

	@Value("${upload.url}")
	private String UPLOAD_URL;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	@Value("${upload.dir}")
	private String UPLOAD_DIR;

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		return objectMetadata;
	}

	private String generateFileName(MultipartFile file) {
		return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
	}

	@Transactional
	public String imageUpload(ReqImageUpload reqData) throws IOException {
		String bucketDir = bucketName + UPLOAD_DIR;
		String dirUrl = bucketName + UPLOAD_DIR + "/";
		String fileName = generateFileName(reqData.getUploadFile());

		amazonS3.putObject(bucketDir, fileName, reqData.getUploadFile().getInputStream(), getObjectMetadata(reqData.getUploadFile()));
		return dirUrl + fileName;
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
