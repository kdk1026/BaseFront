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
		<div id="userName">${user.userNm}</div>
		<form action="/test/logout" method="post">
			<button type="submit" id="btnLogout">Logout</button>
		</form>
		<div>${domain}</div>
	</div>

	<script type="text/javascript">
		$(function() {
			fnCheckLogin();
		});

		function fnCheckLogin() {
			// 쿠키 생성 시, httpOnly false로 해야 스크립트에서 접근 가능

			const token = CommonJS.Cookie.getCookie('accessToken');
			console.log(token);

			/*
			const parseToken = parseJwt(token);
			console.log(parseToken);

			const userInfo = JSON.parse(parseToken.__userInfo__);
			console.log(userInfo);
			*/
		}

		function parseJwt(token) {
			var base64Url = token.split('.')[1];
			var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
			var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function(c) {
		        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
		    }).join(''));
			return JSON.parse(jsonPayload);
		}
	</script>

</body>
</html>