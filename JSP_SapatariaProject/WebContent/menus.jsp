<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Links de Menu</title>
<link rel="stylesheet" type="text/css" href="Estilos.css">
</head>
<body class="bodyMenus">	
		<h1 class="hMenus">Menu</h1>
		<hr /> <a href="conteudoHome.jsp" target="conteudo" class="amenus">
			<h2>Home</h2>
	</a> <a href="conteudoPreco.jsp" target="conteudo" class="amenus">
			<h2>Produtos</h2>
	</a> <a href="conteudoContato.jsp" target="conteudo" class="amenus">
			<h2>Contato</h2>
	</a> <a href="conteudoFranq.jsp" target="conteudo" class="amenus">
			<h2>Franquias</h2>
	</a>
	<hr />
	<%
		if (request.getAttribute("naoCadastrado") != null) {
	%>
	<h4><%=request.getAttribute("naoCadastrado")%></h4>
	<%
		} else
	%>
	<%
		if (request.getAttribute("user") == null) {
	%>
	<form method="post" action="LoginControler">
		<h4 class="h4menus">Área para Assinantes:</h4>
		<p class="pmenus">
			Login <input type="text" maxlength="50" name="login"> Senha <input
				type="password" maxlength="10" name="pass">
			<button type="submit">Entrar</button>
			<button type="reset">Limpar</button>
		</p>
	</form>
	<%
		} else {
	%>
	<h4 class="h4menus">
		Seja Bem Vindo,
		<%=request.getAttribute("user")%></h4>
	<%
		}
	%>
</body>
</html>