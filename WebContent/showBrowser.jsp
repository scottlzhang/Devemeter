<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/canvasjs/canvasjs.min.js"></script>
<script type="text/javascript" src="js/canvasjs/jquery.canvasjs.min.js"></script>
<script type="text/javascript" src="js/jgauge/justgage-1.1.0.min.js"></script>
<script type="text/javascript" src="js/jgauge/raphael-2.1.4.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>

<!-- <script type="text/javascript"><jsp:include page="js/canvasjs/canvasjs.min.js" /></script>
<script type="text/javascript"><jsp:include page="js/canvasjs/jquery.canvasjs.min.js" /></script>-->

<title>Insert title here</title>

</head>
<body>
      <div>
      Wanqing123
    <textarea rows="4" cols="100" id="msgArea" readonly></textarea>
    </div>
    <div>

      <button id="genSwitch">Switch Chart</button>
       <div id="generalChart" style="height: 300px; width: 100%; display:block"></div>
       <div>
	       <a href="#" id="gen_day">Day</a>
	       <a href="#" id="gen_week">Week</a>
	       <a href="#" id="gen_month">Month</a>
       </div>
       
       <div id="trendChart" style="height: 300px; width: 100%; display:block"></div>
       <div>
	       <a href="#" id="trend_week">Week</a>
	       <a href="#" id="trend_month">Month</a>
	       <a href="#" id="trend_year">Year</a>
       </div>

		<div id="productivity" style="width:400px; height:300px; margin-left:35%"></div>
</body>
</html>
