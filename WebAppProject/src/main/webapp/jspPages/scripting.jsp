<?xml version="1.0" encoding="UTF-8" ?>
<%@ page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" info="Elementos de Scripts"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Page 1</title>
</head>
<body>
	<h3>Diretiva de Include</h3>
	<%@ include file="scripting2.jsp"%>

	<h3>Declaracoes</h3>
	<%!public void showServletInfo(JspWriter writer) throws IOException {
		writer.println(this.getServletInfo());
	}%>

	<h3>Scriptles</h3>
	<%
		this.showServletInfo(out);
	%>

	<h3>Declaracoes - Objetos implicitos</h3>
	<%=request%>
	<br />
	<%=response%><br />
	<%=out%><br />
	<%=session%><br />
	<%=config%><br />
	<%=application%><br />
	<%=page%><br />
	<%=pageContext%>

	<h3>Page Context</h3>
	<%
		pageContext.setAttribute("attrTeste", "valorTeste", PageContext.REQUEST_SCOPE);
	%>

	AttrTeste:
	<%=pageContext.findAttribute("attrTeste")%>
	<br /> Escopo:
	<%=pageContext.getAttributesScope("attrTeste")%>
	<br /> Atributos no escopo de requisicao:
	<%=pageContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE).nextElement()%>
	<br />
	<%
		if ("true".equals(request.getParameter("forward"))) {
			pageContext.forward("scripting2.jsp");
		}
		pageContext.include("scripting2.jsp");
	%>
	Teste
</body>
</html>