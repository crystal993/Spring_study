package com.work.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
	@RequestMapping("/main")
	public String main() {
		return "main"; 
	}
	
	@RequestMapping("/joinForm")
	public String joinForm(Model model, HttpSession session) {
		// 로그인 하지 않은 사용자 제공 서비스
		// 로그인한 사용자가 요청하면 로그인이 "로그인 사용자가 이용할 수 없는 서비스입니다."
		
		// 인증여부 체크 
		if (session.getAttribute("memberId") != null && session.getAttribute("dto") != null) {
			model.addAttribute("message","로그인 사용자가 이용할 수 없는 서비스입니다.");
			return "result";
		}
		return "joinForm";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(Model model, HttpSession session) {
		// 로그인 하지 않은 사용자 제공 서비스
		// 로그인한 사용자가 요청하면 로그인이 "로그인 사용자가 이용할 수 없는 서비스입니다."
		
		// 인증여부 체크 
		if (session.getAttribute("memberId") != null && session.getAttribute("dto") != null) {
			model.addAttribute("message","로그인 사용자가 이용할 수 없는 서비스입니다.");
			return "result";
		}
		return "loginForm";
	}
	
	@RequestMapping("/join")
	public String join(String memberId, String memberPw, String name, String mobile, String email, Model model) {
		
		//System.out.println("회원가입 요청"); // 개발중에 디버그 정보 출력, 개발완료후에는 삭제.. 불편
		log.info("### 회원가입요청");
		
		Member dto = Member.builder().memberId(memberId).memberPw(memberPw).name(name).mobile(mobile).
				email(email).manager("없음").build();
		
		log.debug("### " + dto);
		
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
	public String login(String memberId, String memberPw, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		log.debug(session.isNew() + "," + session.getCreationTime());
		log.info("### 로그인 요청");
		log.debug("### " + memberId + "," + memberPw);
		
		// 로그인 회원의 등급 반환 
		String grade = memberService.login(memberId, memberPw);
		
		// 로그인 회원의 도메인 반환 
		Member dto = memberService.loginToMember(memberId, memberPw);
		
		System.out.println("등급 : " + grade);
		if (grade != null && dto != null) {
			// 사용자 인증 처리
			// session.invalidate(); // 혹시라도 기존세션에 있는 세션정보가 존재할수도 있으니 기존세션객체 삭제 : 세션설정 정보 삭제하기 위함 
			// 다시 새로운 세션객체를 생성 => request.getSession(); 
			// getAttributeNames():java.util.Enumeration

			session.setAttribute("memberId", memberId); 
			session.setAttribute("grade", grade);
			session.setAttribute("dto", dto);
			
			session.setAttribute("message","[로그인 사용자]"+memberId);
			
			
			return "main";
			
		} else {
			session.setAttribute("message","[오류] 로그인에 실패했습니다.");
			return "result";
		}
	}
	
	
	@RequestMapping("/memberList")
	public String memberList(Model model, HttpSession session) {
		// 요청에 대한 사용자 인증 및 권한 체킹 부분이 누락 
		
		// 인증여부 체크 
		if (session.getAttribute("memberId") == null || session.getAttribute("dto") == null) {
			model.addAttribute("message","로그인 인증 후 이용하시기 바랍니다.");
			return "result";
		}
		
		// 관리자 권한 여부 체크
		if (!session.getAttribute("grade").equals("A") || 
			!((Member)session.getAttribute("dto")).getGrade().equals("A")) {
			model.addAttribute("message","관리자 권한 획득 후 이용하시기 바랍니다.");
			return "main";
		}
		
		 String grade = (String)session.getAttribute("grade");
		 
		 List<Member> memberList = memberService.memberList(grade);
		 session.setAttribute("grade", grade);
		 session.setAttribute("memberList", memberList);
		return "memberList";
	}
	
	@RequestMapping("/selectMember")
	public String selectMember(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		
		Member dto = memberService.selectMember(memberId);
		session.setAttribute("dto", dto);
		 
		 if(dto == null) {
			 model.addAttribute("message","[오류] 회원 조회에 실패했습니다.");
			 return "result";
		 }
		 
		return "selectMember";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 로그아웃 : 세션객체 가져와서 (DI) 세션설정정보 삭제하고 세션객체 삭제처리
		
		Enumeration<String> attributes = session.getAttributeNames();
		
		while(attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			log.debug("###" + attribute + ":" + session.getAttribute(attribute));
			session.removeAttribute(attribute);
		}
		
		if(session.getAttribute("dto") != null) {
			session.removeAttribute("dto");
		}
		
		if(session.getAttribute("memberId") != null) {
			session.removeAttribute("memberId");
		}
		
		if(session.getAttribute("grade") != null) {
			session.removeAttribute("grade");
		}
		
		session.invalidate();
		return "main";
		
	}
	
	
}
