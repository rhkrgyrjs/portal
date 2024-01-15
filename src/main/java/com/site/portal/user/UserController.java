package com.site.portal.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;

@RequiredArgsConstructor
@Controller
public class UserController 
{
	private final UserService userService;
	
	@GetMapping("/signup")
    public String signupForm(UserInfoForm userInfoForm) 
	{
        return "user_signup";
    }

	@GetMapping("/login")
	public String loginForm()
	{
		return "user_login";
	}
	
	// 회원가입 Post 요청을 처리하는 페이지.
    @PostMapping("/signup")
    public String signup(@Valid UserInfoForm userInfoForm, BindingResult bindingResult) 
    {
    	if (bindingResult.hasErrors())
    		return "user_signup";
    	
    	if (!(userInfoForm.getPassword().equals(userInfoForm.getPassword_val())))
    	{
    		bindingResult.reject("signupFailed", "패스워드가 일치하지 않습니다.");
    		return "user_signup";
    	}
    	try 
    	{
    		this.userService.signupUser(userInfoForm.getName(), userInfoForm.getId(), userInfoForm.getPassword(), userInfoForm.getNickname(), userInfoForm.getPhonenumber(), userInfoForm.getEmail(), userInfoForm.getAddress(), userInfoForm.getBirthdate());
    	}
    	catch (DataIntegrityViolationException e)
    	{
    		bindingResult.reject("signupFailed", "이미 가입된 회원입니다.");
    		return "user_signup";
    	}
    	//return "redirect:/";
        // 결과를 보여줄 HTML 페이지로 이동
        return "user_signup_result";
    }
}
