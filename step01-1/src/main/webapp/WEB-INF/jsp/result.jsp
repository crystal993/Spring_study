<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">
</head>
<body>
<img src="/img/logo.jpg">
<a href="joinForm">회원가입</a>
<a href="loginForm">로그인</a>

<c:if test="${grade == 'A'}">
	<span> 관리자 메뉴 </span>
	<a href="memberList">전체회원조회</a>
</c:if>

<h3>로그인페이지</h3>

<h3>메세지</h3>
<c:if test="${not empty message}">
	${message}
</c:if>

<c:if test="${not empty userId}">
	${userId}
</c:if>


</body>
</html>