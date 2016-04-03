<?xml version="1.0" encoding="US-ASCII" ?>
<%@page import="beans.ClienteBean"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>UseBean 2</title>
</head>
<body>
	<h1>UseBean 2</h1>

	<%
		ClienteBean cliente = new ClienteBean();
		cliente.setNome("Matheus");
		request.setAttribute("cb", cliente);
	%>

	<!-- Caso o atributo ja exista no escopo especificado, qualquer tentativa de definir novos valores para os campos desse atributo sera ignorada, pois
		 a action useBean so avalia o conteudo de seu corpo quando uma nova instancia e criada. Caso o atributo seja encontrado no escopo, tudo que
		 esta dentro da action e ignorado. -->
	<jsp:useBean id="cb" class="beans.ClienteBean" scope="request">
		<jsp:setProperty name="cb" property="nome" value="Gomes" />
	</jsp:useBean>

	<!-- Esta linha definiria o valor do atributo nome para Gomes pois esta fora do corpo de useBean e seria processada -->
	<%-- 	<jsp:setProperty name="cb" property="nome" value="Gomes" /> --%>

	<!-- Caso o atributo especificado em getProperty nao exista, um erro de traducao ocorrera. -->
	<%-- 	<jsp:getProperty property="nomes" name="cb"/> --%>
	<jsp:getProperty property="nome" name="cb" />


</body>
</html>