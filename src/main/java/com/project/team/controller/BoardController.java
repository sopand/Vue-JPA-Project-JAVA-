package com.project.team.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.team.entity.Member;
import com.project.team.req.board.ReqBoardInsert;
import com.project.team.req.board.ReqModifyBoard;
import com.project.team.res.ResResult;
import com.project.team.res.board.ResDetailBoard;
import com.project.team.res.board.ResSelectBoard;
import com.project.team.service.BoardService;
import com.project.team.util.LoginCheck;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Tag(name="게시글 API",description ="게시글 API")
public class BoardController {
	
	private final BoardService boardSRV;
	

	@PostMapping(value = "/create")
	public ResponseEntity<ResResult> insertBoard(@Valid@RequestBody ReqBoardInsert reqData,@LoginCheck @Schema(hidden = true) Member member) throws Exception {
	    ResResult result = boardSRV.boardInsert(reqData, member);
	    return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/{category}")
	public ResponseEntity<ResSelectBoard> selectBoard(@PageableDefault(page = 0, size = 15, sort = "boardSid", direction = Sort.Direction.DESC) Pageable pageable,@PathVariable(value="category") String category){
		ResSelectBoard result=boardSRV.selectBoard(pageable, category);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value="/details/{board_sid}")
	public ResponseEntity<ResDetailBoard> detailBoard(@PathVariable(value="board_sid") Long board_sid){
		ResDetailBoard result=boardSRV.detailBoard(board_sid);
		return ResponseEntity.ok(result);
		
	}
	@PutMapping(value="/modify")
	public ResponseEntity<ResResult> modifyBoard(@LoginCheck @Schema(hidden = true) Member member,@RequestBody ReqModifyBoard reqData) throws Exception{
		ResResult result=boardSRV.modifyBoard(reqData, member);
		return ResponseEntity.ok(result);
	}

}
