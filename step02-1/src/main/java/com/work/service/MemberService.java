package com.work.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.Utility.Utility;
import com.work.dao.MemberDao;
import com.work.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	public MemberService() {
	}
	
	
	/** 로그인 */
	public String login(String memberId, String memberPw) {
		String grade = memberDao.login(memberId, memberPw);
		System.out.println("dao grade: " + grade);
		return grade;
	}

	/** 회원가입 */
	public int join(Member dto) {
		
		dto.setEntryDate(Utility.getCurrentDate());  
		dto.setGrade("G");
		dto.setMileage(0);
		int joinCheck = memberDao.join(dto);
		System.out.println("dao join : "+joinCheck);
		
		return joinCheck;
	}
	
	/** 전체회원 조회 */
	public List<Member> memberList(String grade) {
		
		if (grade.equals("A")) {
			List<Member> memberList = memberDao.selectMemberList();
			return memberList;
		}
		
		return null;
	}

	/** 상세 회원 조회 */
	public Member selectMember(String memberId) {
		if(memberId == null) {
			return null;
		}
		Member dto = memberDao.selectMember(memberId);
		System.out.println("dao selectMember : " + dto);
		return dto;
	}
}
