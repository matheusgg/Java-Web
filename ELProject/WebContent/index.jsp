<?xml version="1.0" encoding="US-ASCII" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>EL Project</title>
</head>
<body>
	<h1>EL Project</h1>

	<jsp:scriptlet>pageContext.setAttribute("attr", "Atributo adicionado no escopo de pagina.", PageContext.PAGE_SCOPE);
			pageContext.setAttribute("attr", "Atributo adicionado no escopo de requisicao.", PageContext.REQUEST_SCOPE);</jsp:scriptlet>

	<!-- EL sao expressoes utilizadas para gerar conteudo dinamico e promover menos scripts em paginas JSP. Comecam com o simbolo de 
		 dolar e o conteudo da EL fica dentro de chaves. Qualquer espaco em branco dentro das chaves e ignorado. -->
	<!-- O uso primario de uma EL e recuperar os valores atributos de escopo. Comecando desde page escope ate application scope. -->
	<!-- EL's apenas avaliam e recuperam valores, por este motivo nao e possivel declarar ou instanciar objetos dentro de uma EL. O valor do conteudo
		 avaliado e passado como argumento para o metoro write de out. -->
	<jsp:text>${attr}</jsp:text>

	<br />
	<jsp:text>${"String de Teste"}</jsp:text>

	<!-- EL's nao suportam concatenacao de Strings -->
	<br />
	<jsp:text>
		<![CDATA[
			<jsp:text>${"String de Teste " + "concatenada"}</jsp:text>
		]]>
	</jsp:text>

	<!-- Porem concatenacoes podem ser feitas utilizando varias EL's seguidas. -->
	<br /> ${"Primeira tese mais"} ${"segunda tese"} possibilitam uma ${"conclusao"}.

	<!-- Nao ha caracteres especiais para realizar o scaping de EL's, deste forma para escapar uma expressao
		 basta inserir uma String dentro da EL com o conteudo que deve ser escapado ou inserir uma "\" antes do dolar -->
	<!-- Primaira opcao -->
	<br /> Escapando ${"${"}

	<!-- Segunda opcao. Com isso a expressao interira e escapada-->
	<br /> Escapando \${10 * 8}
</body>
</html>
