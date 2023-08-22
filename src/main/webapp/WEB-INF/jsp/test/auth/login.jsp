<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/fragment/script.jsp" />

</head>
<body>

	<div>
		<form action="/test/loginProc" method="post" id="loginFrom">
			<input type="text" name="id" placeholder="Id" />
			<input type="password" name="pw" placeholder="Password">
			<input type="submit" value="Login">
		</form>
	</div>

	<script type="text/javascript">
		$(function() {

		});
	</script>

</body>
</html>