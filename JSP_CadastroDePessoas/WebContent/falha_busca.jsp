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
			<h3>
				<h:outputText value="#{agenda.nome}" />
				não foi encontrado(a)!
			</h3>
		</h:form>
		<br /> <h:outputLink value="buscar.jsf">
				<f:verbatim>voltar</f:verbatim>
			</h:outputLink>
	</f:view>
</body>
</html>