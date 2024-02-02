package com.project.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	 Page<Comment> findByBoardBoardSid(Long boardSid, Pageable pageable);
}
