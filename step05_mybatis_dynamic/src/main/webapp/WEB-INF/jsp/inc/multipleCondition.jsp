<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl config  -->   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<link type="text/css" rel="stylesheet" href="../css/common.css">

<style type="text/css">
div.search {
	margin : 10px;
}

input {
	display : inline;
}
</style>


<!-- 다중조건검색 조각페이지 : inc/multipleCondition.jsp -->
<div class="multipleCondition">
	<form action="multipleCondition" method="get">
		<select name="condition">
		    <option value="memberId">아이디</option>
		    <option value="name">이름</option>
		    <option value="grade">등급</option>
		    <option value="mileage">마일리지</option>
		    <option value="manager">담당자</option>
		    <option value="mobile">휴대폰 : 뒷번호 4자리 </option>
		    <option value="email">이메일</option>
		    <option value="memberIdOrEmail">아이디+이메일</option>
		</select>
		<input type="text" placeholder="검색어를 입력해주세요" name="keyword">
		<input type="submit" value="검색">
	</form>
</div>