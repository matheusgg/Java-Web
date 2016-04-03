<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Exemplo JSF</title>
</head>
<body>
	<f:view>
		<h:form>
			<h2>Inserção</h2>
			<br />
			<h3>Entre com os dados abaixo</h3>
			<table>
				<tr>
					<td>Nome:</td>
					<td><h:inputText value="#{agenda.nome}" /></td>
				</tr>
				<tr>
					<td>Endereço:</td>
					<td><h:inputText value="#{agenda.endereco}" /></td>
				</tr>
				<tr>
					<td>Cidade:</td>
					<td><h:inputText value="#{agenda.cidade}" /></td>
				</tr>
				<tr>
					<td>Telefone:</td>
					<td><h:inputText value="#{agenda.telefone}" /></td>
				</tr>
			</table>
			<p>
				<h:commandButton value="Inserir" action="#{agenda.inserir}" />
				<h:commandButton value="Limpar" action="#{agenda.limpar}" />
			</p>
		</h:form>
		<br />
		<h:outputLink value="index.jsf">
			<f:verbatim>Voltar</f:verbatim>
		</h:outputLink>
	</f:view>
</body>
</html>