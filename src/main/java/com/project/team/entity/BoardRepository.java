package com.project.team.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.util.FlagYN;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	
	Page<Board> findAllByDelYnAndCategory(FlagYN delYn, String category, Pageable pageable);
}
