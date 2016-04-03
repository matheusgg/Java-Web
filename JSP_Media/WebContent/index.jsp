<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style type="text/css">
form {
	text-align: center;
}

p {
	text-align: center;
}
</style>
<title>Cálculo de Média</title>
</head>
<body>
	<form action="CalculaMedia" method="post">
		Digite a nota 1: <input type="text" name="n1" /> <br /> Digite a
		nota 2: <input type="text" name="n2" /> <br /> Digite a nota 3: <input
			type="text" name="n3" /> <br /> Digite a nota 4: <input type="text"
			name="n4" /> <br />
		<button type="submit" value="Calcular">Calcular</button>
		<br />
	</form>

	<%
		String media = (String) request.getAttribute("media");
		if (media != null) {
			out.println("<p>A média deste aluno é: " + media + "</p>");
		}
	%>
</body>
</html>