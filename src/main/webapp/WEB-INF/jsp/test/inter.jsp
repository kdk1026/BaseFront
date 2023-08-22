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
		<h2>App Interface Test</h2>

		<div style="margin-bottom: 10px">
			<button type="button" onclick="getDeviceInfo()">디바이스정보</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" onclick="getWifiInfo()">와이파이정보</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" onclick="getSewingMachine()">미싱 연결정보 가져오기</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" onclick="goWifiSetting()">와이파이 설정 이동</button>
		</div>
		<div style="margin-bottom: 20px">
			<button type="button" onclick="goTimeSetting()">날짜 및 시간 설정 이동</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" onclick="resetBobbin()">침수 초기화</button>
		</div>
		<div style="margin-bottom: 10px">
			<button type="button" onclick="fnSendTest1()">모터 테스트1</button>
			<button type="button" onclick="fnSendTest2()">모터 테스트2</button>
			<button type="button" onclick="fnSendTest3()">초기화</button>
		</div>

		<textarea id="textContentOutput" rows="6" cols="30" readonly></textarea>

		<textarea id="output55" rows="6" cols="30" readonly placeholder="사절"></textarea>
		<textarea id="output02" rows="6" cols="30" readonly placeholder="침수"></textarea>
		<textarea id="output54" rows="6" cols="30" readonly placeholder="모터 가동여부"></textarea>
		<input type="text" id="output" placeholder="모터 구동시간 테스트" />
	</div>

	<script type="text/javascript">
	var motorTmInterval;
	var sec = Number(CommonJS.Text.defaultString(sessionStorage.getItem('accSec'), '0'));

	$(function() {
		sec = 0;
		sessionStorage.setItem('initTime', 0);
		sessionStorage.setItem('motorTm', 0);
		sessionStorage.setItem('accSec', 0);
	});

	function fnSetWifiOff(data) {
		alert(data.connect);
	}

	function fnChkSewingMachine(data) {
		alert(data.connect);
	}

	function fnSendTest1() {
		var obj = {};
		obj.cmd = 'C';
		obj.num = '54';
		obj.data = '00001';

		fnSetSewingStatus(obj);
	}
	function fnSendTest2() {
		var obj = {};
		obj.cmd = 'C';
		obj.num = '54';
		obj.data = '00000';

		fnSetSewingStatus(obj);
	}

	function fnSetSewingStatus(data) {
		var results = JSON.stringify(data);

		// 사절
		if ( data.num == '55' ) {
			document.getElementById('output55').innerHTML = results;
		}

		// 침수
		if ( data.num == '02' ) {
			document.getElementById('output02').innerHTML = results;
		}

		// 모터 가동여부
		if ( data.num == '54' ) {
			document.getElementById('output54').innerHTML = results;

			if (data.data == '00001') {
				var initTime = sessionStorage.getItem('initTime');

				if ( initTime == 0 ) {
					var now = new Date();
					sessionStorage.setItem('startTime', now.getTime());
				}

				var now = new Date();
				sessionStorage.setItem('initTime', now.getTime());

                //sec = sec + 1;
                //sessionStorage.setItem('accSec', sec);
			}
			if (data.data == '00000') {
				var now = new Date();
				var endTime = now.getTime();
				var startTime = sessionStorage.getItem('startTime');

				var gapTime = endTime - startTime;
				var accSec = Math.ceil(gapTime / 1000);

				sessionStorage.setItem('accSec', accSec);
			}

			var accSec = Number(sessionStorage.getItem('accSec'));
			var motorTm = Number(sessionStorage.getItem('motorTm'));

			document.getElementById('output').value = (accSec + motorTm);
		}
	}

	function fnSendTest3() {
		sessionStorage.removeItem('accSec');
		sec = 0;
	}
	</script>

</body>
</html>