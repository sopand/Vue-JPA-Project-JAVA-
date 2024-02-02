package com.project.team.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.team.entity.Member;
import com.project.team.req.comment.ReqInsertComment;
import com.project.team.res.ResResult;
import com.project.team.res.board.ResSelectBoard;
import com.project.team.res.comment.ResSelectComment;
import com.project.team.service.CommentService;
import com.project.team.util.LoginCheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Tag(name="댓글 API",description = "댓글 CRUD")
public class CommentController {
	
	private final CommentService commentSRV;
	
	@PostMapping("/")
	@Operation(summary = "댓글 등록하기")
	@ApiResponse(responseCode = "200",description = "댓글 추가 성공")
	@ApiResponse(responseCode = "403",description = "댓글 추가 실패 ")
	public ResponseEntity<ResResult> insertComment(@LoginCheck Member member,@RequestBody @Valid ReqInsertComment reqData){
		ResResult result=commentSRV.insertComment(reqData,member);
		
		if(!result.getSuccess()) {
			return ResponseEntity.status(403).body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{boardSid}")
	@Operation(summary = "댓글 목록 가져오기")
	@ApiResponse(responseCode = "200",description = "댓글 목록 가져오기완료")
	@ApiResponse(responseCode = "404",description = "댓글이 존재하지 않음")
	public ResponseEntity<ResSelectComment> selectComment(@PathVariable(value = "boardSid") Long boardSid,@PageableDefault(page = 0, size = 15, sort = "commentSid", direction = Sort.Direction.DESC) Pageable pageable){
		ResSelectComment result=commentSRV.selectComment(boardSid, pageable);
		return ResponseEntity.ok(result);
	}
}
