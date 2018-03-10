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
	<img src="<c:url value='/${book.image }'/>"/>
	<ul>
		<li>书名:${book.bname }</li>
		<li>作者:${book.author }</li>
		<li>单价:${book.price }</li>
	</ul>
	<form action="<c:url value='/CartServlet'/>" method="post">
		<input type="hidden" name="method" value="add"/>
		<input type="hidden" name="bid" value="${book.bid }"/>
		<input type="text" size="3"	name="count" value="1"/><br>
		<input type="submit" value="购买"/>
	 </form>
</body>
</html>