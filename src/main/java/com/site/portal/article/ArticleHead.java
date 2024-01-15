package com.site.portal.article;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

// 유저가 작성한 글의 헤더 정보(제목, 작성자, 작성일시 등..)을 저장하는 테이블 생성
// 테이블의 조회 속도를 위해 작성자의 정보 등 원문을 제외한 모든 정보는 역정규화(중복저장) 함.
// 유저가 회원탈퇴 할 시 작성한 글 모두 삭제됨 (ON DELETE CASCADE) 

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article_head")
public class ArticleHead 
{ 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_head_id")
    private Long articleId;
	// 글의 고유 ID
	
	@JoinColumn(name="user_id", nullable=false)
	@ManyToOne
	private UserInfo user;
	// 유저와 N:1 참조관계, 유저 회원탈퇴 시 작성글 모두 삭제됨 
	// 유저의 로그인 ID나 유저의 닉네임은 참조하지 않음. 
	
	@JoinColumn(name="category_id", nullable=false)
	@ManyToOne
	private Category category;
	// 글의 카테고리 저장
	// category 테이블 참조한 N:1 관계 (게시글 하나당 카테고리 하나)
	// 카테고리(게시판)이 삭제될 경우, 해당 게시판에 작성된 글 모두 삭제됨. 관리자만 게시판 삭제 가능 
	// 카테고리를 개설한 유저가 탈퇴해도 게시판은 삭제되지 않음.
	
	@Column(name="author_id", nullable=false)
	private String authorId;
	
	@Column(name="author_nickname", nullable=false)
	private String author_nickname;
	// 글 작성자의 정보 역정규화 -> 글 목록 조회(헤더정보)는 자주 일어나기 때문 
	
	
	@Column(name="tag", nullable=true)
	private String tag; 
	// 글의 분류(질문, 토론, 정보글...) 를 저장함. 글의 분류를 따로 저장하지 않을 수 있음.
	// 원래 post_category 테이블을 따로 생성할 계획이었으나, DB가 지나치게 복잡해지는 것을 방지하기 위해 합침.
	// 템플릿에서 태그의 종류 제한할 예정임(직접 입력 불가능, 드롭다운으로 선택)
	
	@Column(name="title", nullable=false, columnDefinition = "TEXT")
	@Size(min=1, max=200)
	private String title;
	// 글의 제목 저장. 최소 1글자 최대 200글자.
	// 이후 DBMS에서 full-text search를 지원하기 위한 인덱스를 생성함.
	
	@Column(name="creation_date", nullable=false)
	private Timestamp creation_date;
	// 글의 작성시간 저장
	
	@Column(name="views", nullable=false)
	private int views = 0;
	// 글의 조회수 저장. 기본값은 0 
	
	@Column(name="likes", nullable=false)
	private int likes = 0;
	// 글의 추천(좋아요)수 저장. 기본값은 0
	
	@Column(name="preview", nullable=false, columnDefinition = "TEXT")
	@Size(max=100)
	private String preview;
	// 글의 미리보기 저장. 해당 글의 처음 200자(200자 미만일 경우 글 전체)를 저장함.
	// 글의 본문을 저장하는 테이블에 접근하는 것을 최소화 하기 위해 저장함.
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="article_body")
	private ArticleBody articleBody;
}
