package com.project.team.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"입력 정보 유효성 인증 실패"),
	SESSION_NOT_FOUND(HttpStatus.NOT_FOUND,"로그인 세션정보를 찾을 수 없습니다.");
	
	private final HttpStatus status;
	private final String message;

}
