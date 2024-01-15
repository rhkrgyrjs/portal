package com.site.portal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.site.portal.user.*;

@SpringBootTest
class PortalApplicationTests 
{
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Test
	void testJpa() 
	{
		UserInfo info = new UserInfo();
		info.setUserLoginId("rhkrgyrjsl");
		info.setAddress("경기도 시흥시 장곡동 진살미길 23 401");
		info.setPhoneNumber("01092440717");
		info.setNickname("kovox");
		info.setName("곽 효건");
		info.setUserLoginPassword("zoo@123456");
		info.setEmail("rhkrgyrjsl@naver.com");
		this.userInfoRepository.save(info);
	}

}
