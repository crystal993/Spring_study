package com.work.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.work.dto.Member;

@Service
public class MemberService {
	
	// ArrayList<Member> 멤버변수 선언 및 생성 : 회원들의 자료 저장구조 
	private List<Member> list = new ArrayList<Member>();
	private Map<String, Member> map = new HashMap<String, Member>();
	
	// initMember() 메서드에서 3명의 회원 등록 구현 
	public List<Member> initMember() {
		Member dto1 = Member.builder().memberId("user01").memberPw("password01").
				name("홍길동").mobile("010-1234-1000").email("user01@work.com").grade("A").build();
		Member dto2 = Member.builder().memberId("user02").memberPw("password02").
				name("강감찬").mobile("010-1234-2000").email("user02@work.com").grade("G").build();
		Member dto3 = Member.builder().memberId("user03").memberPw("password03").
				name("이순신").mobile("010-1234-3000").email("user03@work.com").grade("G").build();
		
		list.add(dto1);
		list.add(dto2);
		list.add(dto3);
		
		map.put("dto1", dto1);
		map.put("dto2", dto2);
		map.put("dto3", dto3);
		
		return list;
	}
	
	
	public MemberService() {
		initMember();
		System.out.println("MemberService() constructor loading");
	}
	
	public int getSize() {
		return list.size();
	}
	
	/** 로그인 */
	public String login(String memberId, String memberPw) {
		
		for (Member dto : list) {
			if (dto.getMemberId().equals(memberId) && dto.getMemberPw().equals(memberPw)) {
				return dto.getGrade();
			}
		}
		return null;
	}

	/** 회원가입 */
	public boolean join(String memberId, String memberPw, String name, String mobile, String email) {
		
		for (Member dto : list) {
			if (dto.getMemberId().equals(memberId) || dto.getName().equals(name) || dto.getMobile().equals(mobile) || dto.getEmail().equals(email)) {
				return false;
			}
		}
		
		if(memberId == null || memberId.trim().length() == 0 ||
		   memberPw == null || memberPw.trim().length() == 0 || 
		   name == null || name.trim().length() == 0 ||
		   mobile == null || mobile.trim().length() == 0 || 
		   email == null || email.trim().length() == 0) {
			return false;
		}

		Member dto = new Member(memberId, memberPw, name, mobile, email, String.valueOf(LocalDate.now()), "G", 0 ,"김밍");
		
		list.add(dto);
		System.out.println(dto);
		
		return true;
	}
	
	/** 전체회원 조회 */
	public List<Member> searchAllMember(String grade) {
		
		if (grade.equals("A")) {
			return list;
		}
		return null;
	}
}
