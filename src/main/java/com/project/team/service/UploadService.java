package com.project.team.service;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.project.team.entity.Board;
import com.project.team.entity.EditorRepository;
import com.project.team.entity.EditorUpload;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {

	private final AmazonS3Client amazonS3;
	
	private final EditorRepository editorRepo;
	
	@Value("${upload.url}")
	private String UPLOAD_URL;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	@Value("${upload.dir}")
	private String UPLOAD_DIR;

	@Value("${cloud.aws.s3.folder}")
	private String FOLDER;
	
	@Transactional
	public EditorUpload addUploadFile(byte[] byteArray, Board board) throws Exception {

		String fileName = UUID.randomUUID().toString().replace("-", "");
		String rpath = FOLDER + "/" + fileName;

		amazonS3.putObject(bucketName, rpath, new ByteArrayInputStream(byteArray), null);

		EditorUpload newUpload = EditorUpload.builder().board(board).fileName(fileName)
				.pathUrl(UPLOAD_URL + bucketName + "/" + rpath).build();
		editorRepo.save(newUpload);
		return newUpload;
	}
}
