<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<%-- Um detalhe importante sobre paginas JSP e que nao e possivel utilizar classes de default package, ou seja, classes sem pacotes,
	 pois isso gerara um erro de compilacao. --%>
<title>Actions</title>
</head>
<body>
	<%-- Action ou StandardActions sao componentes que encapsulam alguma logica e que podem ser reutilizadas em varias partes da aplicacao,
		 ganhando assim escalabilidade e plugabilidade. Todas as actions em JSP pages possuem o prefixo padrao "jsp". Para JSP documents e
		 possivel customizar este prefixo. --%>
	<h1>Actions</h1>

	<!-- A action useBean e utilizada para recuperar algum objeto que ja esta dentro de algum escopo ou criar um
		 novo objeto do tipo especificado. Este objeto criado se torna disponivel para ser utilizado dentro de scriptlets. -->

	<!-- "id": Sera utilizado como chave para procura do atributo no escopo informado. Caso o atributo nao seja localizado, sera utilizado como
		 nome da variavel. -->
	<!-- "class": E utilizado para informar o tipo do objeto que sera criado caso nao exista. -->
	<!-- "scope": Com esse atributo e possivel informar o escopo desse objeto. Primeiro o objeto sera procurado dentro do escopo informado
		 caso nao seja encontrado, um novo objeto sera criado e inserido dentro desse escopo. Quando nao informado o escopo padrao utilizado
		 e o escopo de pagina. -->
	<!-- "beanName": Tem a mesma funcao de class e ambos nao podem ser especificados juntos. A diferenca e que quando beanName e informado,
		 os mecanismos da API JavaBeans sao utilizados para criar um novo objeto do tipo especificado em beanName. Antes de um objeto ser criado,
		 o mecanismo da API JavaBeans tenta localizar um objeto do tipo informado serializado, caso nao encontre, uma nova instancia e criada. 
		 Quando beanName e utilizado o atributo type deve ser declarado. -->
	<!-- "type": E utilizado para especificar o tipo da variavel que sera declarada. Deve ser um supertipo ou o mesmo tipo de class ou beanName.
		 Quando esse atributo e especificado em conjunto com id, um novo objeto nao sera criado caso o atributo nao seja encontrado dentro do escopo
		 informado, gerando assim uma excecao. Quando nao declarado, o valor padrao desse atributo assume o valor de class. -->
	<jsp:useBean id="clienteBean" beanName="beans.ClienteBean" type="beans.ClienteBean"></jsp:useBean>
	<jsp:useBean id="word" beanName="java.lang.String" type="java.lang.Object"></jsp:useBean>
	<jsp:useBean id="num" class="java.lang.Object" type="java.lang.Object"></jsp:useBean>

	<!-- Excecao sera lancada pois o atributo num2 nao existe no escopo de pagina -->
	<%-- 	<jsp:useBean id="num2" type="java.lang.Object"></jsp:useBean> --%>

	<%=clienteBean%>
</body>
</html>