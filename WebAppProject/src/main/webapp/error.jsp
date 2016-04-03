<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Error Page</title>
</head>
<body>
	<%=exception%>
	
	<h3>Atributos de forward</h3>
	<p>
		${requestScope["javax.servlet.forward.request_uri"]} <br />
		${requestScope["javax.servlet.forward.context_path"]} <br />
		${requestScope["javax.servlet.forward.query_string"]} <br />
		${requestScope["javax.servlet.forward.path_info"]} <br />
		${requestScope["javax.servlet.forward.servlet_path"]} <br />
	</p>
	
	<h3>Atributos de error</h3>
	<p>
		${requestScope["javax.servlet.error.exception"]} <br />
		${requestScope["javax.servlet.error.exception_type"]} <br />
		${requestScope["javax.servlet.error.message"]} <br />
		${requestScope["javax.servlet.error.status_code"]} <br />
		${requestScope["javax.servlet.error.servlet_name"]} <br />
		${requestScope["javax.servlet.error.request_uri"]} <br />
	</p>
</body>
</html>