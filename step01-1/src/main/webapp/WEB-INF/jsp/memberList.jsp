<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체회원조회</title>
</head>
<body>
<h3>전체회원조회 페이지</h3>
<img src="/img/logo.jpg">


<c:if test="${not empty list}">
			<table>
				<tr>
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
				
				<c:forEach var="dto" items="${list}">
					<tr> 
						<td><a href="detail?memberId=${dto.member_id }">${dto.member_id }</a></td> 
						<td><a href="controller?action=detail&memberId=${dto.member_id}">${dto.member_id}</a></td> 
						<td>${dto.member_pw }</td>
						<td>${dto.name }</td>
						<td>${dto.mobile }</td>
						<td>${dto.email }</td>
						<td>${dto.entryDate }</td>
						<td>${dto.grade }</td>
						<td>${dto.mileage }</td>
						<td>${dto.manager }</td>
					</tr>
				</c:forEach>
				
			</table>
		</c:if>
		
		<c:if test="${empty list}">
			<h3>등록된 회원이 없습니다.</h3>
		</c:if>
</body>
</html>