<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
</head>
<body>
	<c:forEach items="${bookList }" var="book">
		<div style="float: left; border: 10px; border-color: red; margin: 20px;">
			<a href="<c:url value='/BookServlet?method=findByBid&bid=${book.bid }'/>"><img src="<c:url value='/${book.image }'/>" border="1"/></a>
			<br>
			<a href="<c:url value='/BookServlet?method=findByBid&bid=${book.bid }'/>">${book.bname }</a>
		</div>
	</c:forEach>
</body>
</html>