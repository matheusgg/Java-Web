<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>UseBean Scriptless</title>
</head>
<body>
	<h1>UseBean Scriptless</h1>

	<!-- Mesmo com elementos de script desabilitados, uma instancia de ClienteBean sera criada e ficara disponivel no escopo de pagina -->
	<jsp:useBean id="teste" class="beans.ClienteBean" />
	<jsp:getProperty property="nome" name="teste" />
	<br /> ${teste}
	<br />

	<!-- Nao e possivel utilizar expressoes EL na diretiva de include. Porem e permitido a utilizacao na action de include -->
	<%-- 	<%@ include file="\${param.include}" %> --%>
	<jsp:include page="/${param.include}" />

	<br />
	<jsp:include page="<%=request.getParameter(\"include\")%>" />

	<br />
	<!-- Realizar a inclusao estatica de page.jsp resultara em um erro de traducao, pois page.jsp tenta
		 alterar o contentType, o que e ilegal quando a inclusao estatica e utilizada. -->
	<%-- 	<%@ include file="page.jsp" %> --%>
	
	<!-- Com a inclusao atraves da action include, o mecanismo de include de RequestDispatcher e utilizado, desta forma todas
		 as tentativas de adicionar informacoes em cabecalho serao ignoradas, deste modo, e permitido que page.jsp tente alterar
		 o contentType, porem nao surtira efeito. -->
	<jsp:include page="page.jsp" />
</body>
</html>