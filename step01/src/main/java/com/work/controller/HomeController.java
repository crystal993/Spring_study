package com.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.dto.Member;

@Controller
public class HomeController {
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "메인화면";
	}
	
	@RequestMapping("/array")
	@ResponseBody
	public int[] showArray() {
		//int[] nums = {10, 20, 30};
		int[] nos = new int[5];
		for (int index=0, init=10; index <3; index++, init+=10) {
			nos[index] = init;
		}
		return nos;
	}
	
	@RequestMapping("/member")
	@ResponseBody
	public Member showMember() {
		//회원객체 정보 출력 
		Member dto = new Member("user01","password01","홍길동","01012341000","user01@work.com","2021-07-14","G",1000, "강동원");
		System.out.println(dto);
		//Member(memberId=user01, name=홍길동, email=user01@work.com, entryDate=2021-07-14, grade=G, mileage=1000, manager=강동원)

		return dto;
		//JSON : 
	}
	
	@RequestMapping("/member2")
	@ResponseBody	
	public Member showMember2() {
		//Member dto = new Member("aaa","bbb","강감찬");
		//일부 데이터 지정 객체 생성 
		// 순서도 임의로 지정해서 생성 
		// @Builder (Builder Pattern)
		
		return null;
	}
	
}
