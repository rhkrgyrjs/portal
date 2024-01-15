package com.site.portal.article;

import java.sql.Timestamp;

import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

//댓글에 달린 댓글(대댓글, reply)을 저장하는 테이블
//댓글을 작성한 유저의 정보는 역정규화되어 저장됨
//유저가 회원탈퇴해도 대댓글은 유지됨. 단, 대댓글을 단 댓글 삭제 시 대댓글은 삭제됨 

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article_reply")
public class ArticleReply 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_reply_id")
	private Long articleReplyId;
	// 대댓글의 고유 ID 
	
	@Column(name="text", nullable=false, columnDefinition = "TEXT")
	@Size(min=1, max=500)
	private String text;
	
	@Column(name="creation_time", nullable=false)
	private Timestamp creationTime;
	// 댓글의 작성시각 저장 
	
	@Column(name="user_id", nullable=false)
	private Long userId;
	//대댓글 작성한 유저의 고유ID 저장
	
	@Column(name="author_id", nullable=false)
	private String authorId;
	// 대댓글 작성한 유저의 로그인ID 저장
	
	@Column(name="author_nickname", nullable=false)
	private String authorNickname;
	// 대댓글 작성한 유저의 닉네임 저장
	// 유저의 정보는 역정규화되어 저장(로드 속도 고려)
	
	@JoinColumn(name="article_comment_id", nullable=false)
	@ManyToOne
	private ArticleComment comment;
	// 대댓글이 달린 댓글 참조 
}
