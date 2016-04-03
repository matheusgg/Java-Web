<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<head>
<title>Minha Página JSF</title>
<style type="text/css">
.teste {
	color: red;
}
</style>
	</head>
	<body>
		<h:form>
			<h:outputText value="Bem Vindo!!!" />
			<br />
			<h:inputText id="text" value="#{teste.text}" />
			<h:commandButton value="Copy" action="#{teste.verificaTextoDigitado}" />
		</h:form>
		<br />
		<h:outputLabel value="#{teste.text}" styleClass="teste"></h:outputLabel>
	</body>
</f:view>
</html>