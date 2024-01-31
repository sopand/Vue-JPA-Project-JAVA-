package com.project.team.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByEmail(String email);
}
