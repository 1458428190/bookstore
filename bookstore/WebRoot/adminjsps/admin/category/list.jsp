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
	<h2 align="center">分类列表</h2>
	<div align="center">
	<p style="font-weight:900; color:red">${msg }</p>
	<table border="1" align="center" width="60%">
		<tr>
			<th>分类名称</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${categoryList }" var="category">
			<tr>
				<td>${category.cname }</td>
				<td>
					<a href="<c:url value='/admin/AdminCategoryServlet?method=updatePre&cid=${category.cid }'/>">修改</a> &nbsp;&nbsp;
					<a onclick="return confirm('确定删除?')" href="<c:url value='/admin/AdminCategoryServlet?method=delete&cid=${category.cid }'/>">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>