<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

 <script type="text/javascript">
   var ws = new WebSocket("ws://localhost:8080/Devemeter/WsHandler");
   ws.onopen = function () {
   };

      ws.onmessage = function(message) {
    document.getElementById("msgArea").textContent += message.data + "\n";               
      };

      function postToServer() {
    ws.send(document.getElementById("msg").value);
    document.getElementById("msg").value = "";
   }

   function closeConnect() {
    ws.close();
   }
  </script>
  
</head>
<body>
    <div>
    Wanqing
  <textarea rows="4" cols="100" id="msgArea" readonly></textarea>
  </div>
  <div>
  <input id="msg" type="text"/>
  <button type="submit" id="sendButton" onclick="postToServer()">Send</button>
  </div>
</body>
</html>