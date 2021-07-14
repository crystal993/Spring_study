package com.work.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원 도메인 클래스 9개 속성 정의하세요.
 * toString() 재정의시 : 비밀번호, 휴대폰은 제외
 * @author Playdata
 *
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"memberPw" , "mobile"}, includeFieldNames = false)
@AllArgsConstructor //선행처리 해야할 로직이 있을 때는 어노테이션을 안 쓰고, 직접 생성자를 만들어서 쓰기도 한다.
@Builder
public class Member {
	private String memberId;
//	@ToString.Exclude 
	private String memberPw; 
	private String name;
//	@ToString.Exclude 
	private String mobile;
	private String email;
	private String entryDate;
	private String grade; 
	private int mileage; 
	private String manager;
}
