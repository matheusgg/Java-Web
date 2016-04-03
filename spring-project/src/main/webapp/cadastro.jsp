<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Cadastro</title>
</head>
<body>
	<h1>Página de Cadastro</h1>
	<form:form method="POST" action="cadastraCliente">
		<table>
			<tbody>
				<tr>
					<td>ID</td>
					<td><form:input path="id" /></td>
				</tr>

				<tr>
					<td>Nome</td>
					<td><form:input path="nome" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Cadastrar" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>