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
			<h2>Resultado da Busca</h2>
			<br />
				<table>
					<tr>
						<td>Nome:</td>
						<td><h:outputText value="#{agenda.nome}" /></td>
					</tr>
					<tr>
						<td>Endereço:</td>
						<td><h:outputText value="#{agenda.endereco}" /></td>
					</tr>
					<tr>
						<td>Cidade:</td>
						<td><h:outputText value="#{agenda.cidade}" /></td>
					</tr>
					<tr>
						<td>Telefone:</td>
						<td><h:outputText value="#{agenda.telefone}" /></td>
					</tr>
				</table>
		</h:form>
		<br /> <h:outputLink value="index.jsf">
				<f:verbatim>voltar</f:verbatim>
			</h:outputLink>
	</f:view>
</body>
</html>