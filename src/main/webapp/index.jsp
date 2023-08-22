<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<%
	response.sendRedirect("/init/setting");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>

	<h1>인덱스 페이지</h1>

	<%--
	<a href="/test/hello">Hello 테스트 이동</a><br/>
	<br/>
	<a href="/test/tilesBase">타일즈 레이아웃 베이스</a><br/>
	<a href="/test/tilesNo">타일즈 레이아웃 노</a><br/>

	<a href="/test/login">로그인 테스트</a><br/>
	<a href="/test/chart">차트 테스트</a><br/>
	<a href="/test/paging/1">페이징 테스트</a><br/>
	<a href="/test/file">파일 다운로드 테스트</a><br/>
	--%>

	<a href="${contextPath}/test/inter">인터페이스 테스트 페이지</a><br/><br/>
	<a href="${contextPath}/init/setting">초기 설정 페이지</a><br/>

</body>
</html>