package com.site.portal.article;

import java.sql.Timestamp;
import java.util.List;

import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

// 글의 원문과 댓글을 저장하는 테이블
// article_head : article_body = 1 : 1 대응관계 
// 단순히 정규화 목적으로 테이블을 분리함.

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article_body")
public class ArticleBody 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_body_id")
	private Long articleBodyId;
	// 글 본문의 고유 ID
	
	@Column(name="full_text", nullable=false, columnDefinition = "TEXT")
	@Size(min=1, max=50000)
	private String fullText;
	// 글의 원문 저장 
	// 이후 DBMS에서 full-text search를 지원하기 위한 인덱스를 생성함.
	
    @OneToMany(mappedBy = "articleBody", cascade = CascadeType.ALL)
    private List<ArticleComment> comments;
}
