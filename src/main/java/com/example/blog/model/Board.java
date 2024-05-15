package com.example.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter + Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인 됨.

	@ColumnDefault("0")
	private int count; // 조회수

	@ManyToOne(fetch= FetchType.EAGER) // Many = Board, User = One 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	@JoinColumn(name = "appUserId")
	private AppUser appUser; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	// 자동으로 FK가 만들어짐

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	// mappedBy 연관관게의 주인이 아니다 (난 FK키가 아니예요)
	// DB에 컬럼을 만들지 마세요
	// board를 셀렉트할떄 join문을 위해 값을 얻기 위해 필요한 겁니다.
	private List<Reply> reply;

	@CreationTimestamp
	private Timestamp createDate;

}
