package com.project.team.service;

import org.springframework.stereotype.Service;

import com.project.team.repo.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepo;
	
	
	

}
