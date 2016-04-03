<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.io.Reader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Java Standard Tag Library</title>
</head>
<body>
	<h1>Java Standard Tag Library</h1>

	<h3>Catch</h3>
	<c:catch var="ex">
		<%
			if (request.getParameter("ex") != null) {
					throw new IllegalArgumentException("Parametro de excecao encontrado!");
				}
		%>
	</c:catch>
	Excecao: ${ex}

	<h3>Choose</h3>
	Escopo do atributo:
	<c:choose>
		<c:when test="<%=pageContext.getAttributesScope(\"ex\") == 1%>">
			Page
		</c:when>
		<c:when test="<%=pageContext.getAttributesScope(\"ex\") == 2%>">
			Request
		</c:when>
		<c:when test="<%=pageContext.getAttributesScope(\"ex\") == 3%>">
			Session
		</c:when>
		<c:when test="<%=pageContext.getAttributesScope(\"ex\") == 4%>">
			Application
		</c:when>
		<c:otherwise>
			Nenhum atributo definido!
		</c:otherwise>
	</c:choose>

	<h3>ForEach</h3>
	<%
		pageContext.setAttribute("array", new Integer[] { 03, 11, 23, 35, 47 });
	%>
	<c:forEach items="${array}" begin="0" end="3" var="item" varStatus="status">
		Item: ${item} <br />
		Index: ${status.index} <br />
		Count: ${status.count} <br />
		Current: ${status.current} <br />
		First: ${status.first} <br />
		Last: ${status.last} <br />
		<br />
	</c:forEach>

	<h3>ForTokens</h3>
	<c:forTokens items="a,b,c,d,e" delims="," var="item">
		${item} <br />
	</c:forTokens>

	<h3>If</h3>
	<c:if test="<%=(Math.random() * 100) > 50 ? true : false%>" var="test" scope="session">
		Maior do que 50!!!
	</c:if>
	<br /> Resultado: ${test}

	<h3>Import</h3>
	<c:import url="rd.jsp" varReader="reader">
		<%
			Reader r = (Reader) pageContext.findAttribute("reader");
				out.println(r);
				out.println("<br />");
				out.println("Escopo: " + pageContext.getAttributesScope("reader"));
		%>
	</c:import>

	<h3>Out</h3>
	<c:out value="${param.texto}" default="Nada a exibir!" escapeXml="true" />

	<h3>Param</h3>
	<c:url value="rd.jsp">
		<c:param name="param1" value="valor">valor1</c:param>
		<c:param name="param1">valor2</c:param>
		<c:param name="param1" value="valor">valor2</c:param>
	</c:url>

	<h3>Redirect</h3>
	<c:if test="${param.redirect}">
		<c:redirect url="actions2.jsp" />
	</c:if>

	<h3>Set</h3>
	<c:set value="Matheus Gomes Goes" var="nome" scope="session" />
	Nome: ${nome}

	<h3>Remove</h3>
	<c:remove var="nome" scope="session" />
	Nome: ${nome}

	<h3>URL</h3>
	<c:url scope="request" var="url">
		<jsp:attribute name="value">rd.jsp</jsp:attribute>
		<jsp:body>
			<c:param name="param1" value="teste" />
		</jsp:body>
	</c:url>
	URL: ${url}
</body>
</html>