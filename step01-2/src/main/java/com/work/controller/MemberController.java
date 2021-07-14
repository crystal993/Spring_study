package com.work.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.work.dto.Member;

@Controller
public class MemberController {
	
	// 응답페이지 이동 : jsp
	// application.properties
	// spring.mvc.view.prefix=/WEB-INF/jsp/
	// spring.mvc.view.suffix=.jsp
	@RequestMapping("/main")
	public String main() {
		return "main"; // /WEB-INF/jsp/main.jsp
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("/join")
	public String join(Member dto) {
		System.out.println("회원 가입 요청");
		System.out.println(dto); //Member(user01, 김수정 , user01@work.com, null, null, 0, null)
		return null;
	}
	
//	@RequestMapping("/login")
//	public void login(HttpServletRequest request) {
//		System.out.println("로그인 요청");
//		String memberId = request.getParameter("memberId");
//		String memberPw = request.getParameter("memberPw");
//		System.out.println(memberId + "," + memberPw);
//		//return null;
//	}
	
	@RequestMapping("/login")
	public void login(String memberId, String memberPw) {
		System.out.println("로그인 요청");
		System.out.println(memberId + "," + memberPw);
		//return null;
	}
	
//	@RequestMapping("/login")
//	public void login(Member dto) {
//		System.out.println("로그인 요청");
//		System.out.println(dto.getMemberId() + "," + dto.getMemberPw());
//		//return null;
//	}

// 	로그인 요청
//	null,null
//	@RequestMapping("/login")
//	public void login(Map<String, Object> map) {
//		System.out.println("로그인 요청");
//		System.out.println(map.get("memberId") + "," + map.get("mamberPw"));
//		//return null;
//	}

	

}
