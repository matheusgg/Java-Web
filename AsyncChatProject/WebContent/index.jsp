<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="res/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="res/css/bootstrap.min.css" rel="stylesheet" />

<script type="text/javascript" src="res/js/bootstrap.min.js"></script>
<script type="text/javascript" src="res/js/jQuery.js"></script>

<script type="text/javascript">
	function sendMsg() {
		var msg = $("#txt").val();

		if(msg.length > 0){
			var requestURL = <%="\""+request.getRequestURL()+"AsynServlet?msg=\""%> + msg; 
	
			$.ajax({
				type : "GET",
				url : requestURL
			}).done(
					function(data) {
						$(($("<td>").text(data)).appendTo($("<tr>").appendTo($("#table-msg").children())));
						$("#txt").val("");
					});
		}
	}

	function send(event){
		if(event.keyCode == 13){
			sendMsg();
		}
	}
</script>

<title>Async</title>
</head>
<body>
	<h1>Async</h1>

	<div>
		Mensagem: <input type="text" id="txt" onkeydown="send(event)"/>
		<button type="button" class="btn btn-primary" id="btn" onclick="sendMsg()">Send</button>
	</div>

	<div style="width: 700px">
		<table class="table table-striped table-bordered" id="table-msg">
			<tbody></tbody>
		</table>
	</div>

</body>
</html>
