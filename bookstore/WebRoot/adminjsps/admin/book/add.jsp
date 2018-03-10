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
		<h1>添加图书</h1>
		<p style="color:red;">${msg }</p>
		<form action="<c:url value='/admin/AdminAddBookServlet'/>" method="post" enctype="multipart/form-data">
			图书名称:<input type="text" name="bname" value="${book.bname }"/><br>
			图书图片:<input type="file" name="image"/><br>
			图书单价:<input type="text" name="price" value="${book.price }"/><br>
			图书作者:<input type="text" name="author" value="${book.author }"/><br>
			图书分类:
			<select name="cid">
			<c:forEach items="${categoryList }" var="category">
				<option value="${category.cid }">${category.cname }</option>
			</c:forEach>
		</select><br>
		<input type="submit" value="添加"/>
		</form>
	</div>
</body>
</html>