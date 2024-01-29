package com.project.team.util;

import java.util.Base64;
import java.util.Random;

import com.project.team.entity.Board;
import com.project.team.entity.EditorUpload;
import com.project.team.service.UploadService;


public class Utils {
	
	/** 랜덤코드(Utils.getRandom) 인덱스 0 */
	public static final Integer RANDOM_ONLY_NUMBER = 0;
	
	public static String editorImageUpload(UploadService srvUpload,String content,Board board)
			throws Exception {
		StringBuffer desc = new StringBuffer();

		String strProc = content;
		String strPrev = null;
		int nStart = 0;

		while (true) {
			int start = strProc.indexOf("data:image");
			System.out.println("스타트 : "+start);
			if (start < 0)
				break;
			
			strPrev = strProc.substring(nStart, start);
			System.out.println("PREV 값 : "+strPrev);
			desc.append(strPrev);

			strProc = strProc.substring(start, strProc.length());
			int end = strProc.indexOf("\"/>");
			start = strProc.indexOf(",");
				System.out.println("체크하기"+strProc.substring(start + 1, end));
			byte[] decodedBytes = Base64.getDecoder().decode(strProc.substring(start + 1, end));
			EditorUpload file = srvUpload.addUploadFile(decodedBytes,board);

			desc.append(file.getPathUrl());
			strProc = strProc.substring(end, strProc.length());
		}
		desc.append(strProc.substring(nStart, strProc.length()));
		return desc.toString();

	}
	
	
}
