<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>当前订单</h1>
	<table border="1" width="100%" cellspacing="0" background="black">
		<tr bgcolor="gray" bordercolor="gray">
			<td colspan="6"> 
				订单编号: ${order.oid }&nbsp;&nbsp;成交时间:<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${order.ordertime }"/>&nbsp;&nbsp;
				金额:<font color="red"><b>${order.total }元</b></font>
			</td>
		</tr>
		<c:forEach items="${order.list }" var="orderItem">
		<tr bordercolor="gray" align="center">
			<td>
				<div ><img width="25%" height="25%" src="<c:url value='/${orderItem.book.image }'/>" /></div>
			</td>
			<td>书名:${orderItem.book.bname }</td>
			<td>单价:${orderItem.book.price }</td>
			<td>作者:${orderItem.book.author }</td>
			<td>数量:${orderItem.count }</td>
			<td>小计:${orderItem.subtotal }</td>
		</tr>
		</c:forEach>
	</table>
	<form action="<c:url value='/OrderServlet'/>" method="post">
		<input type="hidden" name="method" value="pay"/>
		<input type="hidden" name="oid"	value="${order.oid }"/>
		收货地址:<input type="text" name="address" value="广东省广州市海珠区赤沙路21号广东财经大学" size="60" /><br>
		选择银行:<br>
		<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行&nbsp;&nbsp;
		<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>中国农业银行<br>
		<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>工商建设银行&nbsp;&nbsp;
		<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行<br>
		<input type="radio" name="pd_FrpId" value="GDB-NET-B2C"/>广发银行&nbsp;&nbsp;
		<input type="radio" name="pd_FrpId" value="POST-NET-B2C"/>中国邮政<br>
		<input type="submit" value="直接结算"/>
	</form>
</body>
</html>