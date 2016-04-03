<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>UseBean</title>
</head>
<body>
	<h1>UseBean</h1>

	<!-- Dentro do corpo da action useBean e possivel inserir qualquer conteudo, desde texto livre ate outras actions para configuracao
		 do bean recem criado. Nao e possivel ter nomes de beans duplicados, pois duas variaveis com o mesmo nome serao criadas dentro
		 do metodo service -->
	<jsp:useBean id="cliente" class="beans.ClienteBean" type="beans.ClienteBean" scope="request">
		<p>ClienteBean</p>

		<!-- Com setProperty e possivel settar informacoes no bean que foi instanciado. property e name sao obrigatorios. -->
		<!-- name: especifica o nome do bean (definido no id) -->
		<!-- property: especifica a propriedade que tera o valor alterado. Caso o coringa * seja utilizado, significa que 
		 os atributos do bean seram preenchidos com os parametros da requisicao que possuirem o mesmo nome dos atributos
		 e que puderem ser convertidos para os tipos esperados. -->
		<jsp:setProperty property="*" name="cliente" />
	</jsp:useBean>

	<!-- Isso resultara em um NullPointer pois o bean cliente3 nao existe -->
	<%-- 	<jsp:setProperty property="nome" name="cliente3" value="Teste" /> --%>

	<jsp:useBean id="cliente2" class="beans.ClienteBean" type="beans.ClienteBean" scope="request" />

	<!-- Definindo um valo manualmente. E possivel utilizar expressoes EL ou scripts para definicao dos valores.
		 Nao e possivel utilizar o atributo value e param ao mesmo tempo. -->
	<jsp:setProperty property="nome" name="cliente2" value="Teste" />
	<jsp:setProperty property="endereco" name="cliente2" value="${param.endereco}" />
	<%-- 	<jsp:setProperty property="endereco" name="cliente2" value="<%=request.getParameter(\"endereco\") %>" /> --%>
	<jsp:setProperty property="nascimento" name="cliente2" param="nascimento" />

	<p>
		<%=cliente%>
	</p>
	<p>
		<%=cliente2%>
	</p>

	<!-- Recuperando atributos do bean -->
	<p>
		<b>Cliente</b> <br />Nome:
		<jsp:getProperty property="nome" name="cliente" />
		<br /> Endereco:
		<jsp:getProperty property="endereco" name="cliente" />
	</p>
</body>
</html>