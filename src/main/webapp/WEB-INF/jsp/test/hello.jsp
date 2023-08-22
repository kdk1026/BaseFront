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
</head>
<body>

	<div>
		Hello, ${name}
	</div>
	<div>
		<spring:eval expression="@environment.getProperty('spring.profiles.active')" var="springProfilesActive" />
		${springProfilesActive}
	</div>

</body>
</html>