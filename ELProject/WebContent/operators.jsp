<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="bean.*" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Operators</title>
</head>
<body>
	<h1>Operators</h1>

	<!-- Existem dois tipos de operadores; OPERADORES DE ACESSO DE COLECOES e OPERADORES DE ACESSO DE PROPRIEDADES.
		 O ponto (".") e os colchetes ("[]")-->

	<!-- O operador ponto pode ser utilizado em objetos que seguem o padrao JavaBeans ou mapas (onde o valor depois do ponto)
		 e utilizado como chave do mapa -->
	<br />${header.cookie}
	<hr />

	<!-- Porem o operador colchetes pode ser utilizado para recuperar valores de mapas, onde o vaor dentro dos colchetes pode
		 ser tanto uma String (chave do mapa) ou um numero (indice de um objeto indexado) -->
	<br />${header['cookie']}
	<hr />

	<!-- O operador ponto nao pode ser utilizado em objetos numericamente indexados, deste modo, e possivel utilizar apenas o
		 operador colchetes. Os tres exemplos abaixo acessam um array pelo indice e sao semanticamente identicos  -->
	<br />${headerValues.cookie["0"]}
	<br />${headerValues.cookie[0]}
	<br />${headerValues.cookie['0']}
	<hr />

	<!-- E possivel utilizar parenteses do lado esquerdo da espressao antes do ponto para melhorar a legibilidade, o contrario nao 
		 e permitido -->
	<br />${(cookie["JSESSIONID"]).name}
	<hr />
	
	<jsp:scriptlet>
		Object[] keys = {"attr", 0};
		pageContext.setAttribute("keys", keys);
	</jsp:scriptlet>
	
	<!-- E possivel aninhar operadores. No exemplo abaixo, a expressao keys["0"] sera avaliada primeiro, e o item da posicao zero desse array
		 sera utilizado como chave para o operador de acesso de propriedades da segunda expressao. -->
	<br />${sessionScope[keys["0"]]}
	<hr />
	
	<jsp:scriptlet>
		MyBean mb = new MyBean();
		mb.setNome("Matheus");
		
		request.setAttribute("mb", mb);
	</jsp:scriptlet>
	
	<!-- Uma excecao ocorrera se um metodo getXxx nao for encontrado na classe. Neste caso, nao existe um metodo getName, logo uma
		 excecao sera lancada. Porem, em expressoes EL, quando uma excecao do tipo NullPoiterException ocorre ou o retorno e null,
		 nada e enviado para a resposta, ou seja, nao sao lancadas excecoes ou erros. -->
	<%-- 	<br /> ${requestScope.mb['name']} --%>
	
	<br /> ${mb["nome"]}
	<br /> ${headerValues.cookie[keys['1']]}
	
</body>
</html>
