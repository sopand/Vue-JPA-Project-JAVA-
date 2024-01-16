package com.project.team.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.team.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
