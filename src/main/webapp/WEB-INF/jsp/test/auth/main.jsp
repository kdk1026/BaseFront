<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<div id="userName">${user.userNm}</div>
		<form action="/test/logout" method="post">
			<button type="submit" id="btnLogout">Logout</button>
		</form>
	</div>

</body>
</html>