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
	<h1 align="center">添加分类</h1>
	<div align="center">
	<p style="font-weight:900; color:red">${msg }</p>
	<form action="<c:url value='/admin/AdminCategoryServlet'/>" method="post">
		<input type="hidden" name="method" value="add"/>
		分类名称:<input type="text" name="cname"/>
		<input type="submit" value="添加分类"/>
	</form>
	</div>
</body>
</html>