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
		<form action="/test/loginProc" method="post" id="loginFrom">
			<input type="text" name="id" placeholder="Id" />
			<input type="password" name="pw" placeholder="Password">
			<input type="submit" value="Login">
		</form>
	</div>

</body>
</html>