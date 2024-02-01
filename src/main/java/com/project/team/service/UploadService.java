package com.project.team.service;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.project.team.entity.Board;
import com.project.team.entity.EditorUpload;
import com.project.team.repository.EditorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadService {

	private final AmazonS3 amazonS3;
	
	private final EditorRepository editorRepo;
	
	@Value("${upload.url}")
	private String UPLOAD_URL;

	@Value("${cloud.aws.s3.bucket}")
	private String BUCKET_NAME;

	@Value("${cloud.aws.s3.directory}")
	private String UPLOAD_DIR;
	
	@Transactional
	public EditorUpload addUploadFile(byte[] byteArray, Board board) throws Exception {

		String uuid = UUID.randomUUID().toString().replace("-", "");
		String rpath = UPLOAD_DIR + "/" + uuid;

		amazonS3.putObject(BUCKET_NAME, rpath, new ByteArrayInputStream(byteArray), null);
		
		EditorUpload newUpload = EditorUpload.builder().board(board).fileName(uuid)
				.pathUrl(UPLOAD_URL+"/" + rpath).build();
		editorRepo.save(newUpload);
		return newUpload;
	}
}
