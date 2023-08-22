<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/fragment/script.jsp" />

</head>
<body>

	<div>
		<h2>Javascript & jQuery Test</h2>

		<div style="margin-bottom: 10px">
			<button type="button" id="btn1" onclick="fnJqueryAjaxTest()">jQuery Ajax</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" id="btn2" onclick="fnVanillaAjax()">Vanilla Ajax</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" id="btn3">jQuery Click</button>
		</div>

	</div>

	<script type="text/javascript">
	$(function() {
		$('#btn3').click(function() {
			alert('jQuery 클릭');
		});
	});

	function fnJqueryAjaxTest() {
		$.commonAjax(true, 'post', '${contextPath}/init/getBplcInfoList', null, {}, function (data) {
			alert(data.length);
		});
	}

	function fnVanillaAjax() {
		CommonJS.Http.commonAjax(true, 'post', "${contextPath}/init/getBplcInfoList", null, {}, function (data) {
			alert(data.length);
		});
	}
	</script>

</body>
</html>