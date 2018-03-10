<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.session_cart==null or fn:length(sessionScope.session_cart.cartItems) eq 0}">
			<h1>购物车为空</h1>
		</c:when>
		<c:otherwise>
	<table border="1" width="100%" cellspacing="0" background="black">
		<tr>
			<td colspan="7" align="right" style="font-size:15pt; font-weight:900">
				<a href="<c:url value='/CartServlet?method=clear'/>">清空购物车</a>
			</td>
		</tr>
		<tr>
			<th>图片</th>
			<th>书名</th>
			<th>作者</th>
			<th>单价</th>
			<th>数量</th>
			<th>小计</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${sessionScope.session_cart.cartItems }" var="cartItem">
		<tr>
			<td align="center"><div><img src="<c:url value='/${cartItem.book.image }'/>"></div></td>
			<td align="center">${cartItem.book.bname }</td>
			<td align="center">${cartItem.book.author }</td>
			<td align="center">${cartItem.book.price }</td>
			<td align="center">${cartItem.count }</td>
			<td align="center">${cartItem.subtotal }</td>
			<td align="center"><a href="<c:url value='/CartServlet?method=delete&bid=${cartItem.book.bid }'/>">删除</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td><td></td><td></td><td></td><td></td><td></td>
			<td align="right">合计:${sessionScope.session_cart.total }元</td>
		</tr>
		<tr>
		<td></td><td></td><td></td><td></td><td></td><td></td>
			<td align="right">
				<a href="<c:url value='/OrderServlet?method=add'/>">一键购买</a>
			</td>
		</tr>
	</table>
		</c:otherwise>
	</c:choose>
</body>
</html>