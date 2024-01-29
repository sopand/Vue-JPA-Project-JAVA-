package com.project.team.entity;

import com.project.team.util.FlagYN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity{
	
	@Column(name = "board_sid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSid;
	
	private String category;
	
	@ManyToOne
	@JoinColumn(name = "member_sid")
	private Member member;
	
	private String title;
	
	private String content;
	
	@Column(name = "del_yn")
	@Enumerated(EnumType.STRING)
	private FlagYN delYn;
	
	
	
	
	public void contentImage(String content) {
		this.content=content;
	}
	
	

}
