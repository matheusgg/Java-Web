<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sec Project - Login</title>

<!-- As restricoes de seguranca do container tambem nao sao aplicadas para recursos javascript e arquivos css -->
<script src="<%=request.getContextPath()%>/res/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/res/js/jQuery.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/res/css/bootstrap.css" />

</head>
<body>
	<form action="j_security_check" method="post">
		<div>
			Usuario: <input size="40" name="j_username" type="text" />
		</div>
		<div>
			Senha: <input size="40" style="margin-left: 8px;" name="j_password" type="password" />
		</div>

		<input type="submit" value="OK" />
	</form>
</body>
</html>