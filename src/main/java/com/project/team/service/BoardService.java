package com.project.team.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.team.entity.Board;
import com.project.team.entity.Member;
import com.project.team.enums.ErrorCode;
import com.project.team.enums.FlagYN;
import com.project.team.exception.CustomException;
import com.project.team.repository.BoardRepository;
import com.project.team.repository.MemberRepository;
import com.project.team.req.board.ReqBoardInsert;
import com.project.team.req.board.ReqModifyBoard;
import com.project.team.res.ResResult;
import com.project.team.res.board.ResDetailBoard;
import com.project.team.res.board.ResSelectBoard;
import com.project.team.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepo;

	private final UploadService upload;

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
	@Transactional(readOnly = true)
	public ResSelectBoard selectBoard(Pageable page, String category) {
		Page<Board> pagingList = boardRepo.findAllByDelYnAndCategory(FlagYN.N, category, page);
		int nowPage = pagingList.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, pagingList.getTotalPages());
		
		List<ResSelectBoard.DTO> result=pagingList.stream()
				.map( data -> ResSelectBoard.DTO.builder()
				.boardSid(data.getBoardSid())
				.title(data.getTitle())
				.memberName(data.getMember().getName())
				.createDate(data.getCreateDate())
				.updateDate(data.getUpdateDate())
				.build()
				).collect(Collectors.toList());
		return ResSelectBoard.builder()
				.nowPage(nowPage)
				.endPage(endPage)
				.startPage(startPage)
				.boardList(result)
				.build();
	}
	
	@Transactional(readOnly = true)
	public ResDetailBoard detailBoard(Long board_sid) {
	
		Board getBoard=boardRepo.findById(board_sid).orElseThrow(() ->new CustomException(ErrorCode.BOARD_NOT_FOUND));
		
		
		return ResDetailBoard.builder()
				.boardSid(getBoard.getBoardSid())
				.title(getBoard.getTitle())
				.content(getBoard.getContent())
				.memberName(getBoard.getMember().getName())
				.memberSid(getBoard.getMember().getMemberSid())
				.createDate(getBoard.getCreateDate())
				.updateDate(getBoard.getUpdateDate())
				.build();
	}
	
	@Transactional
	public ResResult modifyBoard(ReqModifyBoard reqData,Member member) throws Exception {
		Board getBoard=boardRepo.findById(reqData.getBoardSid()).orElseThrow(() ->new CustomException(ErrorCode.BOARD_NOT_FOUND));
		if(!member.getMemberSid().equals(getBoard.getMember().getMemberSid())) {
			throw new CustomException(ErrorCode.SID_DONT_MATCH);
		}
		
		
		String content = Utils.editorImageUpload(upload, reqData.getContent(), getBoard);
		
		
		getBoard.modifyBoard(reqData.getTitle(),content);
		
		return ResResult.builder()
				.success(true)
				.message("게시글 수정에 성공하였습니다.")
				.build();
	}

}
