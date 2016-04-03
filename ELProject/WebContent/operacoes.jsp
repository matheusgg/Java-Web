<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:jsp="http://java.sun.com/JSP/Page">
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Operacoes</title>
</head>
<body>
	<h1>Operacoes</h1>
	<!-- If ternario -->
	${10 < 30 ? "Teste true" : "Teste false"}
	<br />

	<!-- empty operator -->
	\${empty sessionScope.attr}
	<br />

	<p>
		Existe uma ordem de precendencia na avaliacao de expressoes, quando sao encontrados operadores com o mesmo nivel de prioridade, a avaliacao e feita da
		esquerda para a direita: <br />
		<ol>
			<li>[], .</li>
			<li>()</li>
			<li>-, not, !, empty</li>
			<li>*, /, div, %, mod</li>
			<li>+, -</li>
			<li>&lt;, lt, &gt;, gt, &le;, le, &ge;, ge</li>
			<li>==, eq, !=, ne</li>
			<li>&&, and</li>
			<li>||, or</li>
		</ol>
	</p>

	${-10 eq 10 - 20}
	<br /> ${"10" + 20}
	<br />
	<jsp:text>${3 > 2 && 5 lt 6}</jsp:text>

	<!-- jsp:text sempre escapa e elimina o CDATA antes de enviar o conteudo para a resposta, desta forma, somente o que esta dentro do CDATA
		 sera enviado e a expressao EL nao sera avaliada -->
	<br />
	<jsp:text>
		<![CDATA[Teste ${nome}]]>
	</jsp:text>


	<!-- Como essa pagina JSP nao participa da sessao, qualquer tentativa de acessar informacoes da sessao (atraves de scriptlet ou EL), resultara em um
		 erro de traducao. (Porem isso nao ocorre no tomcat) -->
	<br />\${sessionScope.attr}
</body>
</html>
