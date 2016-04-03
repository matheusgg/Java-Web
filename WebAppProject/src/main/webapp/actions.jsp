<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="model.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Actions</title>
</head>
<body>
	<h3>Element / Attribute</h3>
	<jsp:element name="cliente">
		<jsp:element name="nome">
			<jsp:body>Descricao do cliente</jsp:body>
		</jsp:element>
		<jsp:element name="idade">
			<jsp:body>21</jsp:body>
		</jsp:element>
	</jsp:element>

	<h3>Include / Params / Param</h3>
	<jsp:include page="jspPages/scripting2.jsp" flush="false">
		<jsp:param value="valor1" name="param1" />
		<jsp:param value="valor1" name="param2" />
	</jsp:include>

	<h3>Text</h3>
	<jsp:text>${param}</jsp:text>

	<h3>UseBean / setProperty / getProperty</h3>
	<jsp:useBean id="cliente" scope="request" class="model.Cliente">
		<jsp:setProperty name="cliente" property="nome" value="Matheus" />
	</jsp:useBean>
	<jsp:getProperty property="nome" name="cliente" />
	<hr />

	<%
		Cliente c2 = new Cliente();
		c2.setNome("Matheus Gomes");
		pageContext.setAttribute("cliente2", c2, PageContext.REQUEST_SCOPE);
		pageContext.setAttribute("cliente3", c2, PageContext.REQUEST_SCOPE);
	%>
	<jsp:useBean id="cliente2" scope="request" class="model.Cliente">
		<jsp:setProperty name="cliente" property="nome" value="Matheus" />
	</jsp:useBean>
	<jsp:getProperty property="nome" name="cliente2" />
	<hr />

	<jsp:useBean id="cliente3" type="model.Cliente" scope="request" />
	<jsp:setProperty property="*" name="cliente3" />
	<jsp:getProperty property="nome" name="cliente3" />

	<br />
	<jsp:getProperty property="nome" name="cliente2" />
	<hr />

	<jsp:useBean id="cliente4" beanName="model.Cliente" type="model.Cliente">
		<jsp:setProperty property="nome" param="nome2" name="cliente4" />
	</jsp:useBean>
	<jsp:getProperty property="nome" name="cliente4" />
</body>
</html>