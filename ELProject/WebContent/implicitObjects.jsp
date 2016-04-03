<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Implicit Objects</title>
</head>
<body>
	<h1>Implicit Objects</h1>

	<!-- PageContext -->
	PageContext: ${pageContext}
	<hr />

	<!-- Um mapa contendo todos os atributos do escopo de pagina -->
	PageScope: ${pageScope}
	<hr />

	<!-- Um mapa contendo todos os atributos do escopo de requisicao -->
	RequestScope: ${requestScope}
	<hr />

	<!-- Um mapa contendo todos os atributos do escopo de sessao -->
	SessionScope: ${sessionScope}
	<hr />

	<!-- Um mapa contendo todos os atributos do escopo de aplicacao. (obs.: Esta escapado pois possui muitos valores) -->
	ApplicationScope: \${applicationScope}
	<hr />

	<!-- Um mapa contendo apenas o primeiro valore dos parametros de requisicao -->
	Param: ${param}
	<hr />

	<!-- Um mapa contendo todos os valores (um array de Strings) dos parametros da requisicao -->
	ParamValues: ${paramValues}
	<hr />

	<!-- Um mapa contendo apenas o primeiro valor de todos os cabecalhos -->
	Header: ${header}
	<hr />

	<!-- Um mapa contendo todos os valores (array de Strings) de todos os cabecalhos -->
	HeaderValues: ${headerValues}
	<hr />

	<!-- Um mapa contendo os nomes de todos os cookies e um objeto Cookie para cada cookie representando o valor do mesmo -->
	Cookie: ${cookie}
	<hr />
	
	<!-- Um mapa contendo todos os parametros de inicializacao de contexto -->
	initParam: ${initParam}
</body>
</html>
