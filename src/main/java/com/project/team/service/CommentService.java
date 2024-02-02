package com.project.team.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.team.entity.Board;
import com.project.team.entity.Comment;
import com.project.team.entity.Member;
import com.project.team.enums.ErrorCode;
import com.project.team.exception.CustomException;
import com.project.team.repository.BoardRepository;
import com.project.team.repository.CommentRepository;
import com.project.team.req.comment.ReqInsertComment;
import com.project.team.res.ResResult;
import com.project.team.res.comment.ResSelectComment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepo;

	private final BoardRepository boardRepo;

	@Transactional
	public ResResult insertComment(ReqInsertComment reqData, Member member) {
		Board boardData = boardRepo.findById(reqData.getBoardSid())
				.orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

		Comment newComment = Comment.builder().member(member).board(boardData).content(reqData.getContent()).build();

		commentRepo.save(newComment);

		if (newComment.getCommentSid() == null) {
			ResResult.builder().success(false).message("댓글 등록에 실패하였습니다").build();
		}
		return ResResult.builder().success(true).message("댓글 등록이 완료되었습니다").build();
	}

	public ResSelectComment selectComment(Long boardSid, Pageable pageable) {
		Page<Comment> commentList = commentRepo.findByBoardBoardSid(boardSid, pageable);
		ResSelectComment result = new ResSelectComment();
		for (Comment item : commentList) {
			result.getCommentList().add(ResSelectComment.DTO.builder()
					.comment_sid(item.getCommentSid())
					.content(item.getContent())
					.createDate(item.getCreateDate())
					.updateDate(item.getUpdateDate())
					.build());
		}
		
		return result;

	}
}
