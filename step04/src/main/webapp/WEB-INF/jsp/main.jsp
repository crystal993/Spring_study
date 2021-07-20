<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link type="text/css" rel="stylesheet" href="/css/common.css">
</head>
<body>
<img src="/img/logo.jpg">
<a href="joinForm">회원가입</a>
<a href="loginForm">로그인</a>

<!-- 관리자 권한 전체회원조회 서비스 제공 -->
<c:if test="${grade eq 'A'}">
	<span> [관리자] </span>
	<a href="memberList">전체회원조회</a>
</c:if>

<h3>메인페이지</h3>
<c:if test="${not empty message}">
	결과메세지 : ${message}
</c:if>

</body>
</html>