package com.work.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.dto.Member;
import com.work.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
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
	
	
//	@RequestMapping("/login")
//	public void login(HttpServletRequest request) {
//		System.out.println("로그인 요청");
//		String memberId = request.getParameter("memberId");
//		String memberPw = request.getParameter("memberPw");
//		System.out.println(memberId + "," + memberPw);
//		//return null;
//	}
	
	@RequestMapping("/login")
	public String login(String memberId, String memberPw, Model model) {
		System.out.println("로그인 요청");
		System.out.println(memberId + "," + memberPw);
		
		String grade = memberService.login(memberId, memberPw);
		
		System.out.println("등급 : " + grade);
		if (grade != null) {
			model.addAttribute("message","[로그인 사용자]"+memberId);
		} else {
			model.addAttribute("message","[오류] 로그인에 실패했습니다.");
		}
		return "result";
		//return null;
	}
	
	@RequestMapping( "/login/param")
	public ModelAndView loginMap(@RequestParam(value = "id") String memberId, 
						   @RequestParam(value= "pw") String memberPw) {
		System.out.println("로그인 요청");
		System.out.println(memberId + "," + memberPw);
		
		//응답위한 객체 생성 
		ModelAndView mav = new ModelAndView();
		mav.addObject("message","로그인정보");
		mav.addObject("userId", memberId);
		mav.setViewName("result");
		
		String grade = memberService.login(memberId, memberPw);
		
		System.out.println("등급 : " + grade);
//		if (grade != null) {
//			model.addAttribute("message","[로그인 사용자]"+memberId);
//		} else {
//			model.addAttribute("message","[오류] 로그인에 실패했습니다.");
//		}
		return mav;
		//return null;
	}
	
	@RequestMapping("/login/null")
	public String loginNull(@RequestParam(required = true, defaultValue = "user01") String memberId, 
			   				@RequestParam(required = true, defaultValue = "password01") String memberPw, Model model) {
		System.out.println("로그인 요청");
		System.out.println(memberId + "," + memberPw);
		
		String grade = memberService.login(memberId, memberPw);
		
		System.out.println("등급 : " + grade);
		if (grade != null) {
			model.addAttribute("grade",grade);
			model.addAttribute("message","[로그인 사용자]"+memberId);
		} else {
			model.addAttribute("message","[오류] 로그인에 실패했습니다.");
		}
		return "main";
		//return null;
	}
	
	
	
	
//	@RequestMapping("/join")
//	public String join(Member dto) {
//		System.out.println("회원 가입 요청");
//		System.out.println(dto); //Member(user01, 김수정 , user01@work.com, null, null, 0, null)
//		return null;
//	}	
	
	@RequestMapping("/join")
	public String join(String memberId, String memberPw, String name, String mobile, String email, Model model) {
		System.out.println("회원가입 요청");

		boolean joinCheck = memberService.join(memberId, memberPw, name, mobile, email);
		System.out.println(joinCheck);
		if (!joinCheck) {
			model.addAttribute("message","[오류] 회원가입에 실패했습니다.");
			return "result";
		} 
			
		return "loginForm";
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
	
	//       /member/size는 POST요청만 받겠다는 의미이다.
//	@RequestMapping(value = "/member/size", method = RequestMethod.POST)
//	@ResponseBody
//	public int size() {
//		return memberService.getSize();
//	}
	
//	@PostMapping("/member/size")
	@GetMapping("/member/size")
	@ResponseBody
	public int size() {
		return memberService.getSize();
	}
	
	@RequestMapping("/memberList")
	public String searchAllMember(String grade, Model model) {
		
		 List<Member> list = memberService.searchAllMember(grade);
		 model.addAttribute("grade",grade);
		 
		 for (Member dto : list) {
			 System.out.println(dto);
		 }
		 
		
		 
		return "memberList";
	}

}
