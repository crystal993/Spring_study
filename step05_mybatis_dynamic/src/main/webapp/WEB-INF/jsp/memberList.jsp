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
<link rel="stylesheet" type="text/css" href="/css/common.css">
</head>
<body>



<h3>전체회원조회 페이지</h3>
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


<!-- 다중조건검색 조각페이지 삽입 -->
<jsp:include page="inc/multipleCondition.jsp"/>


<c:if test="${not empty list}">
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
				
				<!-- 검색조건에 해당하는 결과가 없는 경우 메세지 출력 -->
				<c:if test="${not empty message}">
					<tr>
						<th colspan=10>${message}</th>
					</tr>
				</c:if>
				
				<c:forEach items="${list}" var="dto" varStatus="status">
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
		
		<c:if test="${empty list}">
			<h3>등록된 회원이 없습니다.</h3>
		</c:if>
</body>
</html>