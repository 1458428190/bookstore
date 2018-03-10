 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GDUFE网上书城</title>
</head>
<body>
	<div align="center">
	<h1>登录</h1>
	<p style="color: red">${msg }</p>
	<form action="<c:url value='/UserServlet'/>" method="post" target="top">
		<input type="hidden" name="method" value="login"/>
		用户名: <input type="text" name="username" value="${user.username }"/> <span style="color: red">${errors.username }</span><br>
		密  码:<input type="password" name="password" value="${user.password }"/> <span style="color: red">${errors.password }</span><br>
		<input type="submit" value="登录"/>
	</form>
	</div>
</body>
</html>