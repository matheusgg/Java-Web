<?xml version="1.0" encoding="UTF-8" ?>

<!-- Toda funcao deve estar associada a um namespace, aqui o prefixo fn foi mapeado para o namespace http://el-project.com -->
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:fn="http://el-project.com" version="2.0">
	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Functions</title>
</head>
<body>
	<h1>Functions</h1>

	<!-- Funcoes EL sao uma forma de realizar tarefas comuns que sao necessarias com frequencia e estao fora do escopo de uma EL. Functions EL estao
		 associadas com tag libs e por isso precisam ser declaradas dento do descritor de tag tld. El Functions mapeam metodos estaticos Java para funcoes EL.-->
	${fn:contains(param.nome, 'a')}
</body>
	</html>
</jsp:root>