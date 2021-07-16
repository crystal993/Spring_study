<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체회원조회</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
<style type="text/css">
	table, tr, th, td {
	margin : 10px;
	padding : 5px;
	border: 1px solid lightblue;			
}
</style>
</head>
<body>
<h3>전체회원조회 페이지</h3>
<img src="/img/logo.jpg">
<a href="joinForm">회원가입</a>
<a href="loginForm">로그인</a>

<c:if test="${not empty memberList}">
			<table>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>휴대폰</th>
					<th>이메일</th>
					<th>가입일</th>
					<th>등급</th>
					<th>마일리지</th>
					<th>담당자</th>
				</tr>
				
				<c:forEach items="${memberList}" var="dto" varStatus="status">
					<tr> 
						<td>${status.count}</td>
						<td><a href="selectMember?memberId=${dto.memberId}">${dto.memberId}</a></td>
						<td>
							<c:set var="memberPw" value="${dto.memberPw}"/>
							${fn:substring(memberPw, 0, 2)}
							<c:forEach var="no" begin="2" end="${fn:length(memberPw)}" step="1">*</c:forEach>
						</td>
						<td>${dto.name}</td>
						<td>${dto.mobile}</td>
						<td>${dto.email}</td>
						<td>${dto.entryDate}</td>						
						<td>
							${dto.grade}
							<c:choose>
								<c:when test="${dto.grade == 'A'}">[관리자]</c:when>
								<c:when test="${dto.grade == 'S'}">[우수 회원]</c:when>
								<c:when test="${dto.grade == 'G'}">[일반 회원]</c:when>
							</c:choose>
						</td>
						<td>
						<!-- Edge 통화기호 깨짐 type = "currency | number | percent", currencySymbol="$" -->
						<!-- <fmt:formatNumber type="currency" value="${dto.mileage}"/> -->
						 <fmt:formatNumber value="${dto.mileage}" pattern="\#,###.##"/>
						</td>
						<td>${dto.manager}</td>
					</tr>
				</c:forEach>
				
			</table>
		</c:if>
		
		<c:if test="${empty memberList}">
			<h3>등록된 회원이 없습니다.</h3>
		</c:if>
</body>
</html>