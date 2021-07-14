<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
<link type="text/css" rel="stylesheet" href="css/common.css">
</head>
<body>
<!-- 컨트롤러 구현해보기 : joinForm.jsp 이동 -->
<a href="joinForm">회원가입</a>
<!-- loginForm.jsp 이동 -->
<a href="loginForm">로그인</a>
<h3>로그인페이지</h3>
<img src="/img/logo.jpg">
<form action="login" method="post">
	<input type="text" name="memberId"/>	
	<input type="password" name="memberPw"/>
	<input type="submit" value="로그인" />
	<input type="reset" value="취소" />
</form>

</body>
</html>