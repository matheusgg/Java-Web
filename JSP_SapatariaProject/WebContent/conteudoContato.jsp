<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contato</title>
<link rel="stylesheet" type="text/css" href="Estilos.css">
<script type="text/javascript">
	function cadastrado() {
		window.alert("Comentário cadastrado com sucesso!");
	}
<%if (request.getAttribute("sucesso") != null) {%>
	cadastrado();
<%}%>
	
</script>
</head>
<body class="bodyConteudo">
	<h1 class="h1ConteudoContato">Contato</h1>
	<hr>
	<br />
	<h1 class="h1ConteudoContato">Deixe seu comentário:</h1>
	<form method="post" action="CommentControler">
		<table align="center">
			<tr>
				<td>*Nome: <input size=30 type="text" name="txtNome"></td>
			</tr>
			<tr>
				<td>*E-mail: <input size=30 type="text" name="txtEmail"></td>
				<td><input type="radio" name="op" value="1" checked="checked" /></td>
				<td>Reclamação</td>
				<td><input type="radio" name="op" value="2" checked="checked" /></td>
				<td>Sugestão</td>
				<td><input type="radio" name="op" value="3" checked="checked" /></td>
				<td>Dúvida</td>
			</tr>
			<tr>
				<td>Deseja receber anúncios? <input type="checkbox"
					name="checkYes" value="yes" />Sim
				</td>
			</tr>
			<tr>
				<td><textarea name="txtMensagem" rows="10" cols="40">*Digite aqui a sua mensagem</textarea>
				</td>
			</tr>
			<tr>
				<td><button type="submit">Enviar</button>
					<button type="reset">Limpar</button></td>
			</tr>
			<tr>
		</table>
	</form>
</body>
</html>