package com.site.portal.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

import com.site.portal.article.ArticleHead;

// 카테고리(게시판) 정보를 저장하기 위한 테이블.
// 카테고리 고유ID, 생성한 유저(의 ID), 게시판에 대한 설명을 저장함.
// 게시판을 개설한 유저가 탈퇴해도 카테고리는 남아있음. (개설한 유저는 NULL처리 -> ON DELETE SET NULL)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long community_category_id;
	// 백엔드에서 게시판을 구분하기 위해 사용되는 게시판의 고유 ID 
	// PK 생성은 GeneratedValue 어노테이션을 활용했음. 
	
	@Column(name="creator_user_id", nullable=false)
	private Long creatorUserId;
	
	@Column(name="creator_id", nullable=false)
	private String creatorId;
	
	@Column(name="creator_nickname", nullable=false)
	private String creatorNickname;
	// 해당 게시판을 생성한 유저의 고유 ID, 로그인 ID, 닉네임 저장함
	// 해당 유저가 회원탈퇴 할 시에도 유저의 정보는 유지됨 (참조 관계로 테이블을 선언하지 않았음)
	// -> 이유는 유저가 회원탈퇴 할 시에도 게시판을 생성한 유저의 정보를 저장하는 편이 낫다고 생각했기 때문 
	
	@Column(name="description", nullable=true, columnDefinition = "TEXT")
	@Size(max=500)
	private String description;
	// 게시판에 대한 설명을 저장함.
	// 최대 500글자, 공백 허용함. 
	
	@Column(name="creation_date", nullable=false)
	private Date creationDate;
	// 게시판의 생성일자 저장함. 
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleHead> articleHead;
	// 카테고리(게시판) : 게시글 = 1 : N (하나의 게시판에 여러 개의 게시글 존재 가능) 
}
