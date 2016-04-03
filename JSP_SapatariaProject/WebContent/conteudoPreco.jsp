<%@page import="java.sql.ResultSet"%>
<%@page import="br.prime.controler.ProdutosControler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nossos Produtos</title>
<link rel="stylesheet" type="text/css" href="Estilos.css">
</head>
<body class="bodyConteudo">
	<h1 class="h1ConteudoPreco">
		Nossos Produtos
	</h1>
	<hr />
	<%
		ResultSet rs = null;
		ProdutosControler pc = new ProdutosControler();
		pc.doPost(request, response);
		if (request.getAttribute("produtos") != null) {
			rs = (ResultSet) request.getAttribute("produtos");
		}
	%>

	<table align="center" border="1">
		<tr>
			<th>Produto</th>
			<th><b>Nome</b></th>
			<th><b>Descrição</b></th>
			<th><b>Valor</b></th>
		</tr>
		<%
			while (rs.next()) {
		%>
		<tr>
			<td width="40%"><img alt="<%=rs.getString("nome")%>" src="<%=rs.getString("urlImage")%>" height="100" width="200"/></td>
			<td><%=rs.getString("nome")%></td>
			<td><%=rs.getString("descricao")%></td>
			<td>R$ <%=rs.getDouble("valor")%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>