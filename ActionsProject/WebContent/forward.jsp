<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Forward</title>
</head>
<body>
	<h1>Forward</h1>

	<!-- forward realiza um redirecionamento da mesma forma que o mecanismo de forward de RequestDispatcher. Alem disso tambem e possivel
		 passar parametros com a action param. -->
	<jsp:forward page="/AppServlet">
		<jsp:param value="Teste" name="Parametro1" />
		<jsp:param value="${cookie}" name="Parametro2" />
		<jsp:param value="<%=request.getRequestURI()%>" name="Parametro3" />
	</jsp:forward>
</body>
</html>