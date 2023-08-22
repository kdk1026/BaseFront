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

	<form action="${contextPath}/test/fileDown" method="post">
		<button type="submit">다운로드</button>
	</form>

	<br/><br/>

	<form action="${contextPath}/file/download" method="post">
		<input type="hidden" name="destFilePath" value="/upload/dcmt/" />
       	<input type="hidden" name="fileNewNm" value="ad454df5-54c0-45a6-9035-183c06214dc7.xlsx" />
    	<input type="hidden" name="fileOrgNm" value="새 스프레드시트 문서.xlsx" />
    	<button type="submit">다운로드2</button>
	</form>

	<br/><br/>

	<ul>
		<li>
			<a class="file" href="javascript:void(0)">다운로드3</a>
			<input type="hidden" name="destFilePath" value="/upload/dcmt/">
			<input type="hidden" name="fileNewNm" value="ad454df5-54c0-45a6-9035-183c06214dc7.xlsx">
			<input type="hidden" name="fileOrgNm" value="새 스프레드시트 문서.xlsx">
		</li>
	</ul>

	<form action="${contextPath}/file/download" method="post" id="frm">
		<input type="hidden" name="destFilePath" />
       	<input type="hidden" name="fileNewNm" />
    	<input type="hidden" name="fileOrgNm" />
	</form>

  <script>
  	$(function() {
  		fnDataFileDownload();
  	});

  	function fnDataFileDownload() {
		$('.file').on('click', function() {
			var destFilePath = $(this).closest('li').find('input[name="destFilePath"]').val();
			var fileNewNm = $(this).closest('li').find('input[name="fileNewNm"]').val();
			var fileOrgNm = $(this).closest('li').find('input[name="fileOrgNm"]').val();

			$('#frm').find('input[name="destFilePath"]').val(destFilePath);
			$('#frm').find('input[name="fileNewNm"]').val(fileNewNm);
			$('#frm').find('input[name="fileOrgNm"]').val(fileOrgNm);

			$('#frm').submit();
		});
  	}

  </script>

</body>
</html>