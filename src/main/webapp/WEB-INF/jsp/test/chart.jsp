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
		<div id="main" style="width:600px; height:400px;"></div>
		<div id="currTime"></div>
	</div>

	<script type="text/javascript">
		$(function() {
			fnDrawChart();
			fnDisplayTime();
		});

		function fnDrawChart() {
			const data = ${data};
			//console.log(data);

	        const cateArr = [];
	        const dataArr = [];

	        data.forEach(function(data) {
	            //console.log( data );
	            cateArr.push( data.opNo );
	            dataArr.push( data.time );
	        });

	        //console.log(cateArr);
	        //console.log(dataArr);

			var chartDom = document.getElementById('main');
			var myChart = echarts.init(chartDom);
			var option;

			option = {
			  xAxis: {
			    type: 'category',
			    data: cateArr
			  },
			  yAxis: {
			    type: 'value',
			    min: 0,
			    max: 100,
			    splitNumber: 10
			  },
			  series: [
			    {
			      data: dataArr,
			      type: 'bar'
			    }
			  ],
			  tooltip: {
				  trigger: 'item',
			  }
			};

			option && myChart.setOption(option);
		}

		function fnDisplayTime() {
			moment.locale('ko');
			//const currDate = moment().format('YYYY-MM-DD');
			//const currTime = moment().format('LT');
			//const currDateTime = currDate + ' ' + '(' + currTime + ')';

			const currDateTime = moment().format('YYYY-MM-DD (A hh:mm)');

			$('#currTime').html(currDateTime);
		}

		setInterval("fnDisplayTime()", (1000));
	</script>

</body>
</html>