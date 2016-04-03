<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Data Types</title>
</head>
<body>
	<h1>Data Types</h1>

	<!-- A conversao dos resultados acontece de acordo com o tipo esperado, nos casos abaixo, todos os resultados serao convertidos
		 para String, desta forma, esses valores poderao ser enviados para a resposta. Porem, se em algum lugar, o tipo esperado fosse
		 diferente de String, o mecanismo de coersao da EL tentaria converter (fazer um cast) do valor para o tipo esperado. -->

	<br /> Boolean: ${true}
	<br /> Integer: ${10}
	<br /> Float: ${10.3} ou ${.3}

	<!-- Valores nulos nao sao enviados para a resposta -->
	<br /> Null: ${null}
	<br /> Strings: ${"Minha String"} ou ${'"Minha String com aspas"'} ou "${'Minha String com aspas'}"
</body>
</html>
