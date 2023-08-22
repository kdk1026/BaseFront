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

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css" integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">

<jsp:include page="/WEB-INF/jsp/fragment/script.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container">
		<nav>
			<ul class="pagination">
				<c:set var="page" value="${paging}" scope="request" />
				<c:set var="formId" value="testFrm" scope="request" />
				<jsp:include page="/WEB-INF/jsp/fragment/testPaging.jsp" />
			</ul>
		</nav>

		<form action="" id="testFrm" method="get">
		</form>
	</div>

	<script>
		$(function() {

		});

		function fnGo(formId, pageNo) {
			const $frm = $('#'+formId);
			$frm.attr('action', '/test/paging/'+pageNo);
			$frm.submit();
		}
	</script>

</body>
</html>