<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=4.0, minimum-scale=1.0, user-scalable=yes, target-densitydpi=medium-dpi">
<title>COEA</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/footer.css">

<!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"> -->
<link rel="stylesheet" href="/css/lib/jquery-ui.css">

<jsp:include page="/WEB-INF/jsp/fragment/script.jsp" />

<script>
	$(function() {
		$.ajaxSetup({
			beforeSend: function () {
				$('#ajaxLoad').show();
			},
			complete: function () {
				$('#ajaxLoad').hide();
			}
		});
	});
</script>

</head>
<body>
	<div id="ajaxLoad" style="display: none;">
		<p style="text-align: center; left: 50%; top: 50%; position: absolute; transform:translate(-50%, -50%); z-index: 100;">
			<img alt="" src="${contextPath}/img/loading.gif">
		</p>
	</div>

	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />

	<script>
		$(function() {
			fnDisplayTime();
		});

		function fnDisplayTime() {
			moment.locale('ko');

			const lang = "${lang}";
			if ( lang ) {
				moment.locale(lang);
			}

			const currDate = moment().format('YYYY-MM-DD');
			const currTime = moment().format('( A hh:mm )');

			document.querySelector('.ft-date').innerHTML = currDate;
			document.querySelector('.ft-clock').innerHTML = currTime;
		}

		setInterval("fnDisplayTime()", (1000));
	</script>

</body>
</html>