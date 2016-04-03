<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Página de Login</title>
<style type="text/css">
form {
	margin:200px;
	text-align: center;
}

table {
	margin-right: auto;
	margin-left: auto;
}

body {
	background: url(back.png) 50px 0px;
}
</style>
</head>
<body>
	<form action="LoginServlet" method="post">
		Usuário <input name="user" type="text" maxlength="50" /> <br />
		Senha <input name="pass" type="password" maxlength="50" /> <br />
		<table>
			<tr>
				<td><button type="submit" value="Login">Entrar</button></td>
				<td><button type="reset">Limpar</button></td>
			</tr>
		</table>
	</form>	
</body>
</html>