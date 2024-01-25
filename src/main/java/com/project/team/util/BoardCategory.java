package com.project.team.util;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

public enum BoardCategory {
	NOTICE("01", "공지사항"),
	COMUNITY("02", "커뮤니티"),
	QNA("03","질문사항");
	@Getter
	private final String code;
	@Getter
	private final String code_name;

	BoardCategory(String code, String code_name) {
		this.code = code;
		this.code_name = code_name;
	}

	public String code() {
		return this.code;
	}

	public String code_name() {
		return this.code_name;
	}
	
	private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
		    Stream.of(values()).collect(Collectors.toMap(BoardCategory::getCode, BoardCategory::code_name)));

		public static BoardCategory of(final String code) {
		    return BoardCategory.valueOf(CODE_MAP.get(code));
		}
}
