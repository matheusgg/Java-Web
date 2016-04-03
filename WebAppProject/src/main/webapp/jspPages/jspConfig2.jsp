<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/xml" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" isELIgnored="false"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JspConfig 2</title>
</head>
<body>
	#{jspPropertyGroup} <br />
	
	<h3 style="display: ${fn:contains(header['Content-Type'], 'multipart/form-data') ? 'inline' : 'none'}">Arquivos Enviados</h3>
	<br />
	
	<%
		if (request.getContentType() != null && request.getContentType().contains("multipart/form-data")) {
			Collection<Part> files = request.getParts();
			for (Part part : files) {
				String[] disposition = part.getHeader("content-disposition").split(";");
				String fileName = disposition[disposition.length - 1].split("=")[1];
				out.println(fileName);
			}
		}
	%>
</body>
</html>