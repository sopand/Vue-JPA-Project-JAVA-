package com.project.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.entity.Board;
import com.project.team.enums.FlagYN;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	
	Page<Board> findAllByDelYnAndCategory(FlagYN del_yn, String category, Pageable pageable);
}
