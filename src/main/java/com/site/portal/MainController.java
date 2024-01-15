package com.site.portal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.site.portal.user.UserInfo;
import com.site.portal.user.UserInfoRepository;
import java.sql.Date;

@Controller
public class MainController 
{
	@Autowired
	private UserInfoRepository userInfoRepository;

	@GetMapping("/test")
	@ResponseBody
	public String test()
	{
		/*Date birthDate = Date.valueOf("2000-07-24");
			UserInfo info = new UserInfo();
			info.setUserLoginId("rhkrgyrjsl");
			info.setBirthDate(birthDate);
			info.setAddress("경기도 시흥시 장곡동 진살미길 23 401");
			info.setPhoneNumber("01092440717");
			info.setNickname("kovox");
			info.setName("곽 효건");
			info.setUserLoginPassword("zoo@123456");
			info.setEmail("rhkrgyrjsl@naver.com");
			this.userInfoRepository.save(info);*/
		return "testing...gasddglgl";
	}
}
