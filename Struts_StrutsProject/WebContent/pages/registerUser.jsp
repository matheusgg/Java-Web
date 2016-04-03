<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Struts App - Registro</title>
</head>
<body>
	<h1>Nova conta</h1>
	<br />
	<html:form action="/actions/registerUser"> 
	
		Email: 
		<input type="text" name="email" />
		<br /> 
		
		Senha: 
		<input type="password" name="senha" />
		<br />

		<input type="submit" value="Efetuar Registro" />
	</html:form>
</body>
</html>