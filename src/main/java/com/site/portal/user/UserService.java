package com.site.portal.user;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

// 회원가입, 로그인, 아이디찾기 등 실질적 기능들 구현하는 곳 

@RequiredArgsConstructor
@Service
public class UserService 
{
	private final UserInfoRepository userInfoRepository;
	
	// 회원가입 메소드 
	public UserInfo signupUser(String name, String id, String password, String nickname, String phonenumber, String email, String address, String birthdate)
	{
		UserInfo user = new UserInfo();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPw = passwordEncoder.encode(password);
		Date date = null;
		try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(birthdate);
            date = new Date(utilDate.getTime());
        } catch (Exception e) {}
		user.setName(name);
		user.setUserLoginId(id);
		user.setUserLoginPassword(hashedPw);
		user.setNickname(nickname);
		user.setPhoneNumber(phonenumber);
		user.setEmail(email);
		user.setAddress(address);
		user.setBirthDate(date);
		this.userInfoRepository.save(user);
		return user;
	}
}
