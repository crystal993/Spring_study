package com.work.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		//JSON : {"memberId":"user01","memberPw":"password01","name":"호길동","mobile":"01012341000","email":"user01@work.com","entryDate":"2021-07-14","grade":"G","mileage":1000,"manager":"강동원"}
	}
	
	@RequestMapping("/member2")
	@ResponseBody	
	public Member showMember2() {
		//Member dto = new Member("aaa","bbb","강감찬");
		//일부 데이터 지정 객체 생성 
		// 순서도 임의로 지정해서 생성 
		// @Builder (Builder Pattern)
		// Builder를 이용한 객체 생성방법 : 클래스명.builder().프로퍼티명(data).프로퍼티명(data).build
		return Member.builder().memberId("crystal").mobile("010-1234-1111").
				name("김수정").email("crystal@work.com").build();
	}
	
	@RequestMapping("/list")
	@ResponseBody	
	public List<String> showList() {
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("안녕");
		return list; // JSON : [10,20,30]
	}
	
	@RequestMapping("/map")
	@ResponseBody	
	public Map<String, Member> showMap() {
		Member dto1 = Member.builder().memberId("user01").name("홍").mobile("010-1234-1000").grade("G").build();
		Member dto2 = Member.builder().memberId("user02").name("홍").mobile("010-1234-2000").grade("G").build();
		Member dto3 = Member.builder().memberId("user03").name("홍").mobile("010-1234-3000").grade("G").build();
		
		Map<String, Member> map1 = new HashMap<String, Member>();
		map1.put(dto1.getMemberId(), dto1);
		map1.put(dto2.getMemberId(), dto2);
		map1.put(dto3.getMemberId(), dto3);
		
		Map<String, Member> map2 = new LinkedHashMap<String, Member>();
		map2.put(dto1.getMemberId(), dto1);
		map2.put(dto2.getMemberId(), dto2);
		map2.put(dto3.getMemberId(), dto3);
		
		//return map1; //HashMap : 순서 보장 하지 않음.
		return map2; //LinkedHashMap : 순서를 보장함.
	}

}