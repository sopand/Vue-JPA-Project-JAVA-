package com.project.team.entity;

import com.project.team.util.FlagYN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="tb_member")
public class Member extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_sid")
	private Long memberSid;
	
	private String email;
	
	private String password;
	
	private String name;
	
	@Column(name="del_yn")
	@Enumerated(EnumType.STRING)
	private FlagYN delYn;
	
	@Builder
	public Member(String email,String password,String name,FlagYN delYn) {
		this.email=email;
		this.password=password;
		this.name=name;
		this.delYn=delYn;
	}
	

}
