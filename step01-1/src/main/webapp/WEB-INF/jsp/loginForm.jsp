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
<!-- loginForm.jsp 이동 -->
<a href="loginForm">로그인</a>
<h3>로그인페이지</h3>

<form action="login" method="post">
	<input type="text" name="memberId"/>	
	<input type="password" name="memberPw"/>
	<input type="submit" value="로그인" />
	<input type="reset" value="취소" />
</form>


<h3>로그인페이지 [action="login/param" name="id",name="pw"]</h3>

<form action="login/param" method="post">
	<input type="text" name="id"/>	
	<input type="password" name="pw"/>
	<input type="submit" value="로그인" />
	<input type="reset" value="취소" />
</form>

<h3>로그인페이지 [name="aaa",name="bbb"]</h3>

<form action="login/null" method="post">
	<input type="text" name="aaa"/>	
	<input type="password" name="bbb"/>
	<input type="submit" value="로그인" />
	<input type="reset" value="취소" />
</form>

</body>
</html>