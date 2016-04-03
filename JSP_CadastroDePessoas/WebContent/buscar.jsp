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
			<h2>Busca</h2>
			<br />
			Digite o nome:
			<h:inputText id="nome" value="#{agenda.nome}" />
			<h:commandButton value="OK" action="#{agenda.buscar}" />
			<h:commandButton value="Limpar" action="#{agenda.limpar}" />
		</h:form>
		<br />
		<h:outputLink value="index.jsf">
			<f:verbatim>Voltar</f:verbatim>
		</h:outputLink>
	</f:view>
</body>
</html>