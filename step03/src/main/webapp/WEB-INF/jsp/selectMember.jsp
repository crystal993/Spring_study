<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 회원조회 페이지</title>
<style type="text/css">
	table, tr, th, td {
	margin : 10px;
	padding : 5px;
	border: 1px solid lightblue;			
}
</style>
</head>
<body>
<h3>상세 회원조회 페이지</h3>
<img src="/img/logo.jpg">
<c:choose>
		<c:when test="${empty memberId || empty dto }">
			<a href="joinForm">회원가입</a>
			<a href="loginForm">로그인</a>
		</c:when>
		
		<c:otherwise>
			<div>로그정보 : ${memberId} [${dto.grade}]</div>
			<a href="logout">로그아웃</a>
			<a href="selectMember">내정보 조회</a>
			
			<!-- 관리자 권한 전체회원조회 서비스 제공 -->
			<c:if test="${grade eq 'A'}">
				<span> [관리자] </span>
				<a href="memberList">전체회원조회</a>
			</c:if>
		</c:otherwise>
	</c:choose>

<c:if test="${not empty dto}">
	<table>
		<tr> 
			<td>아이디</td>
			<td>${dto.memberId}</td>
		</tr>
		
		<tr> 
			<td>비밀번호</td>
			<td>
				<c:set var="memberPw" value="${dto.memberPw}"/>
				${fn:substring(memberPw, 0, 2)}
				<c:forEach var="no" begin="2" end="${fn:length(memberPw)}" step="1">*</c:forEach>
			</td>
		</tr>
		
		<tr> 
			<td>이름</td>
			<td>${dto.name}</td>
		</tr>
		
		<tr> 
			<td>휴대폰</td>
			<td>${dto.mobile}</td>
		</tr>
		
		<tr> 
			<td>이메일</td>
			<td>${dto.email}</td>
		</tr>
		
		<tr> 
			<td>가입일</td>
			<td>${dto.entryDate}</td>
		</tr>
		
		<tr> 
			<td>등급</td>
			<td>
				${dto.grade}
				<c:choose>
					<c:when test="${dto.grade == 'A'}">[관리자]</c:when>
					<c:when test="${dto.grade == 'S'}">[우수 회원]</c:when>
					<c:when test="${dto.grade == 'G'}">[일반 회원]</c:when>
				</c:choose>
			</td>
		</tr>
		
		<tr> 
			<td>마일리지</td>
			<td>
			<fmt:formatNumber value="${dto.mileage}" pattern="\#,###.##"/>
			</td>
		</tr>
		
		<tr> 
			<td>담당자</td>
			<td>${dto.manager}</td>
		</tr>
	
	</table>
</c:if>

</body>
</html>