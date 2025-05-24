<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/fragment/script.jsp" />
</head>
<body>

	<jsp:include page="/WEB-INF/decorators/template/baseHeader.jsp" />

	<sitemesh:write property='body' />

	<jsp:include page="/WEB-INF/decorators/template/baseFooter.jsp" />

</body>
</html>