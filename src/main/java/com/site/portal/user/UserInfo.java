package com.site.portal.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Date;
import java.util.*;

import com.site.portal.article.ArticleHead;
import com.site.portal.category.*;

// 유저의 정보를 저장하기 위한 user_info 테이블에 대한 엔티티.
// 유저의 고유ID, 유저ID, 유저PW(해싱됨), 닉네임, 전화번호, 이메일, 이름, 생일, 주소 등 유저의 정보 저장함. 
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user_info")
public class UserInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	// 백엔드에서 유저를 구분하기 위해 사용되는 고유 ID. 편의성을 위해 로그인 ID와는 별도로 선언함. 
	// PK 생성은 GeneratedValue 어노테이션을 활용했음. 

	@Column(name="id", nullable=false, unique=true)
	@Size(min=8, max=20)
	@Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message="아이디는 8~20자의 영어와 숫자 조합만 가능합니다.")
	private String userLoginId;
	// 유저가 로그인 시 입력하는 ID. 최소 8자 최대 20자, 영어/숫자 조합 가능
	// user_info 테이블의 PK는 userId 이지만, 로그인 ID의 중복 역시 피해야 하기에 unique 속성을 지정함.
	
	@Column(name="password_hash", length=60, nullable=false)
	private String userLoginPassword;
	// BCrypt 해시된 유저의 비밀번호 저장 
	// 비밀번호는 8~20자의 영어,숫자,특수기호 포함해야함 -> 제약조건 나중에 걸자. 
	
	@Column(name="nickname", nullable=false)
	@Size(min=4, max=16)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{4,16}$", message="닉네임은 4~16자의 한글,영어와 숫자 조합만 가능합니다.")
	private String nickname;
	
	@Column(name="phone_number", length=11, nullable=false)
	private String phoneNumber;
	// 유저의 전화번호 저장 (대쉬(-)를 제외한 11개의 숫자 저장함)
	
	@Column(name="email", nullable=false)
	@Size(max=255)
	@Pattern(regexp="^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",message="이메일 형식이 올바르지 않습니다.")
	private String email;
	// 유저의 이메일 저장. 최대 255자까지 허용함.
	
	@Column(name="name", nullable=false)
	@Size(max=30)
	private String name;
	// 유저의 이름 저장함.
	
	@Column(name="birthdate", nullable=false)
	private Date birthDate;
	// 유저의 생년월일 저장 (년/월/일) 
	
	@Column(name="address", nullable=false)
	private String address;
	// 유저의 주소 저장
	// API를 활용할까 고민 중 
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleHead> articleHead;
	// 유저 : 게시글 헤더 = 1 : N (한 유저가 여러 개의 게시글 작성 가능) 
	
}
