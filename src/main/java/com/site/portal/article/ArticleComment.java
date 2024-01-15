package com.site.portal.article;


import java.sql.Timestamp;
import java.util.List;

import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

// 게시글에 달린 댓글을 저장하는 테이블
// 댓글을 작성한 유저의 정보는 역정규화되어 저장됨
// 유저가 회원탈퇴해도 댓글은 유지됨.

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article_comment")
public class ArticleComment 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_comment_id")
	private Long articleCommentId;
	// 댓글의 고유 ID 
	
	@ManyToOne
    @JoinColumn(name = "article_body_id", nullable = false)
    private ArticleBody articleBody;
	// 해당 댓글이 어느 게시글(본문)에 달려있는지 저장 
	
	@Column(name="text", nullable=false, columnDefinition = "TEXT")
	@Size(min=1, max=500)
	private String text;
	
	@Column(name="creation_time", nullable=false)
	private Timestamp creationTime;
	// 댓글의 작성시각 저장 
	
	@Column(name="user_id", nullable=false)
	private Long userId;
	// 댓글 작성한 유저의 고유ID 저장
	
	@Column(name="author_id", nullable=false)
	private String authorId;
	// 댓글 작성한 유저의 로그인ID 저장
	
	@Column(name="author_nickname", nullable=false)
	private String authorNickname;
	// 댓글 작성한 유저의 닉네임 저장
	// 유저의 정보는 역정규화되어 저장(로드 속도 고려)
	
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleReply> articleReply;
	
}
