<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script type="text/javascript"><jsp:include page="js/canvasjs/canvasjs.min.js" /></script>
<script type="text/javascript"><jsp:include page="js/canvasjs/jquery.canvasjs.min.js" /></script>

<title>Insert title here</title>

 <script type="text/javascript">
   var ws = new WebSocket("ws://localhost:8080/Devemeter/WsHandler");
   ws.onopen = function () {
   };

      ws.onmessage = function(message) {
    	document.getElementById("msgArea").textContent += message.data + "\n";
    	chart.options.data[0].dataPoints[3].y += 1; 
    	chart.render();
      };

      function postToServer() {
    ws.send(document.getElementById("msg").value);
    document.getElementById("msg").value = "";
    
   }

   function closeConnect() {
    ws.close();
   }
  </script>

<script type="text/javascript">
window.onload = function () {
    chart = new CanvasJS.Chart("chartContainer",
    {
     
      axisY: {
        maximum: 50
      },
      data: [
	      {
	        type: "bar",
	        dataPoints: [
	        { y: 5, label: "Python"},
	        { y: 6, label: "Java"},
	        { y: 9, label: "Javascript"},
	        { y: 3, label: "PHP"},
	        { y: 5, label: "Perl"},
	        ]
	      }
      ]
    });

chart.render();


}</script>

</head>
<body>
    <div>
  <textarea rows="4" cols="100" id="msgArea" readonly></textarea>
  </div>
  <div>
  <input id="msg" type="text"/>
  <button type="submit" id="sendButton" onclick="postToServer()">Send</button>
  </div>
 <div id="chartContainer" style="height: 300px; width: 100%;">
  </div>
</body>
</html>
