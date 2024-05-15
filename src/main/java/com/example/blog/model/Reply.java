package com.example.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter + Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라나다
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 200)
	private String content; 
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="AppUserId")
	private AppUser appUser;

	@CreationTimestamp
	private Timestamp createDate;
}
