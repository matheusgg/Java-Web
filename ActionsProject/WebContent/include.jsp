<?xml version="1.0" encoding="US-ASCII" ?>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII" />
<title>Include</title>
</head>
<body>
	<h1>Include</h1>

	<!-- Com a action include e possivel incluir conteudo dinamico dentro de uma JSP. Essa action utiliza o mecanismo de include
		 de RequestDispatcher, promovendo assim uma menor quantidade de scripts nas paginas para realizar inclusoes de recursos
		 atraves de um RequestDispatcher. O atributo "flush" especifica se antes da inclusao do recurso, o conteudo da resposta
		 deva ser enviado para o cliente, diminuindo assim a quantidade de informacoes a ser enviada apos a inclusao. -->
	<!-- E possivel especificar parametros que serao enviado para o recurso que esta sendo incluido -->
	<jsp:include page="/AppServlet" flush="false">
		<jsp:param name="paramTeste" value="Teste" />
	</jsp:include>

</body>
</html>