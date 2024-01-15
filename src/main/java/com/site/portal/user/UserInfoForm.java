package com.site.portal.user;

import jakarta.validation.constraints.*;
import lombok.*;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

// 유저의 정보를 저장하는 폼
// 

@Getter
@Setter
public class UserInfoForm 
{

	@Pattern(regexp = "^[a-zA-Z0-9]{8,20}$", message="아이디는 8~20자의 영어와 숫자 조합만 가능합니다.")
	private String id;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[a-zA-Z\\d@$!%*#?&]{8,20}$", message="비밀번호는 영어 대문자, 영어 소문자, 숫자, 특수문자를 포함해 8~20자로 만들어야 합니다.")
	private String password;
	
	@Pattern(regexp="^.{1,}$", message="사용할 패스워드를 확인해주세요.")
	private String password_val;
	
	@NotBlank(message = "이름은 필수항목입니다(최대 30자).")
	@Size(min=1, max=30)
	private String name;
	
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{4,16}$", message="닉네임은 4~16자의 한글,영어와 숫자 조합만 가능합니다.")
	private String nickname;
	
	@Pattern(regexp = "^\\d{11}$", message="전화번호는 -(대쉬) 없이, 11자리의 숫자를 입력하세요.")
	private String phonenumber;
	
	@NotBlank(message = "이메일은 필수항목입니다.")
	@Email
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birthdate;
	
	@Size(max=255)
	@Pattern(regexp="^.{1,}$", message="주소를 입력해 주세요(최대 255글자).")
	private String address;
}
