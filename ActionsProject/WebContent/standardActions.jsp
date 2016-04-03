<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>StandardActions</title>
</head>
<body>
	<h1>StandardActions</h1>

	<!-- Com jsp:element e possivel criar elementos estruturados de acordo com a sintaxe XML onde o clemento cliente sera gerado
		 e possuira o atributo nome com valor Matheus -->
	<!-- Com jsp:body e possivel especificar um conteudo para o elemento -->
	<jsp:element name="cliente">
		<jsp:attribute name="nome">Matheus</jsp:attribute>
		<jsp:body>Corpo do elemento</jsp:body>
	</jsp:element>

	<!-- A action jsp:attribute possui outro uso. Com ele e possivel especificar atributos para outras actions -->
	<!-- Assim com jsp:attribute, jsp:body pode ser utilizada em conjunto com outras actions. Neste caso a unica utilizade e prover legibilidade
		 para diferenciar o que e um atributo e onde comeca a definicao dos valores desse bean. -->
	<jsp:useBean id="cliente">
		<jsp:attribute name="class">beans.ClienteBean</jsp:attribute>
		<jsp:attribute name="type">beans.ClienteBean</jsp:attribute>

		<jsp:body>
			<jsp:setProperty name="cliente" property="*" />
		</jsp:body>
	</jsp:useBean>

	<br />
	<jsp:expression>cliente</jsp:expression>
</body>
</html>