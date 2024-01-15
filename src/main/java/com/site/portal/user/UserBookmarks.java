package com.site.portal.user;

import java.sql.Timestamp;

import com.site.portal.article.ArticleComment;
import com.site.portal.article.ArticleHead;
import com.site.portal.category.Category;
import com.site.portal.user.UserInfo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Timestamp;

// 유저가 즐겨찾기한 게시판을 저장하기 위한 테이블 

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user_bookmarks")
public class UserBookmarks 
{
	@Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;
	// 즐겨찾기 한 유저 

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    // 즐겨찾기 된 카테고리 
}
