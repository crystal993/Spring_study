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

import lombok.Builder;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
	@RequestMapping("/main")
	public String main() {
		return "main"; 
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
	public String join(String memberId, String memberPw, String name, String mobile, String email, Model model) {
		System.out.println("회원가입 요청");
		
		Member dto = Member.builder().memberId(memberId).memberPw(memberPw).name(name).mobile(mobile).
				email(email).manager("없음").build();
		
		int result = memberService.join(dto);
		System.out.println(result);
		if (result == 1) {
			model.addAttribute("message","[회원가입성공] 회원가입에 성공하셨습니다.");
			return "loginForm";
		} else {
			model.addAttribute("message","[오류] 회원가입에 실패했습니다.");
			return "result";
		}
	}
	
	
	@RequestMapping("/login")
	public String login(String memberId, String memberPw, Model model) {
		System.out.println("로그인 요청");
		System.out.println(memberId + "," + memberPw);
		
		String grade = memberService.login(memberId, memberPw);
		
		System.out.println("등급 : " + grade);
		if (grade != null) {
			model.addAttribute("message","[로그인 사용자]"+memberId);
			model.addAttribute("grade", grade);
			
		} else {
			model.addAttribute("message","[오류] 로그인에 실패했습니다.");
		}
		return "result";
		//return null;
	}
	
	
	@RequestMapping("/memberList")
	public String memberList(String grade, Model model) {
		 grade = "A";
		 List<Member> memberList = memberService.memberList(grade);
		 model.addAttribute("grade", grade);
		 model.addAttribute("memberList", memberList);
		 
		 if(memberList == null) {
			 model.addAttribute("message","[오류] 전체회원 조회에 실패했습니다.");
			 return "result";
		 }
		 
		return "memberList";
	}
	
	@RequestMapping("/selectMember")
	public String selectMember(String memberId, Model model) {
		 Member dto = memberService.selectMember(memberId);
		 System.out.println(dto);
		 model.addAttribute("dto", dto);
		 
		 if(dto == null) {
			 model.addAttribute("message","[오류] 회원 조회에 실패했습니다.");
			 return "result";
		 }
		 
		return "selectMember";
	}


}
