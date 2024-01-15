package com.site.portal.user;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService
{
	private final UserInfoRepository userInfoRepository;
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException
	{

		passwordEncoder = new BCryptPasswordEncoder();
		Optional<UserInfo> _siteUser = this.userInfoRepository.findByUserLoginId(userLoginId);
				if (_siteUser.isEmpty()) 
					throw new UsernameNotFoundException("로그인에 실패했습니다.");
				
				UserInfo siteUser = _siteUser.get(); List<GrantedAuthority> authorities = new ArrayList<>(); if ("admin".equals(userLoginId)) 
				{
					authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue())) ;
				} 
				else 
				{
					authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
				}
				return new User(siteUser.getUserLoginId(), passwordEncoder.encode(siteUser.getUserLoginPassword()),authorities);
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception 
	{
		return authenticationConfiguration.getAuthenticationManager();
	}
}
