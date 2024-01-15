package com.site.portal.article;

import java.sql.Timestamp;

import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

// 어느 유저가 어느 글에 좋아요를 표시했나 저장하는 테이블
// 하나의 게시글을 여러 번 추천 불가능하게 하는 기능 구현을 위해 존재 
// 게시물을 추천한 유저가 탈퇴하더라도 좋아요 갯수는 유지되도록 구현함. 

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="article_likes")
public class ArticleLikes 
{  
	@Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
	// 해당 글에 좋아요를 표시한 유저의 고유 ID 
	
    @Id
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleHead articleHead;
    // 좋아요가 표시된 글 
}
