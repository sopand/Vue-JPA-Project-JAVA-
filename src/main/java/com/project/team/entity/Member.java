package com.project.team.entity;

import java.security.Timestamp;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity(name="member")
@Table(name="tb_member")
public class Member extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="member_sid")
	private Long memberSid;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name= "name")
	private String name;
	
	@Enumerated(EnumType.STRING)
	private FlagYN del_yn;
	
	@Column(name = "delete_date")
	private Timestamp deleteDate;
	

}
