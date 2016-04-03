<?xml version="1.0" encoding="US-ASCII" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=US-ASCII" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Index</title>
</head>
<body>
	<h1>Index</h1>

	<jsp:text>#{requestScope}</jsp:text>

	<!-- Essa EL sera ignorada pois foi assim configurado no web.xml -->
	<jsp:text>${requestScope}</jsp:text>

	<%--<c:teste></c:teste>--%>
</body>
</html>
