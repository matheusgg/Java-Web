<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"/>

<style type="text/css">
	.estiloHeader{
		float: right;
	}
</style>

<!-- A autenticacao pelo container e restricoes de segunranca nao sao aplicadas para recursos incluidos 
	 ou redirecionamentos (include ou forward) -->
<div class="estiloHeader">
	<h3>Seja bem vindo <jsp:expression>request.getRemoteUser()</jsp:expression></h3>
</div>
</jsp:root>