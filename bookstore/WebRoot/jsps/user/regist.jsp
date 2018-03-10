<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<h1>注册</h1>
	<p style="color: red;font-weight: 900">${msg }</p>
	<form action="<c:url value='/UserServlet'/>" method="post">
		<input type="hidden" name="method" value="regist"/>
		用户名:<input type="text" name="username" value="${user.username }"/><span style="color:red;">${errors.username }</span><br>
		密码:<input type="password" name="password" value="${user.password }"/><span style="color:red;">${errors.password }</span><br>
		邮箱:<input type="text" name="email" value="${user.email }"/><span style="color:red;">${errors.email }</span><br>
		<input type="submit" value="注册"/>
	</form>
	</div>
</body>
</html>