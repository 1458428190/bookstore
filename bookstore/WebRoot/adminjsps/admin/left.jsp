<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>

<script type="text/javascript">
	var bar1 = new Q6MenuBar("bar1","lulu网络图书商城");
	function load()
	{
		bar1.colorStyle = 2;
		bar1.config.imgDir = "<c:url value='/menu/img'/>"
		bar1.config.radioButton=true;
		bar1.add("分类管理","查看分类","<c:url value='/admin/AdminCategoryServlet?method=findAll'/>", "body");
		bar1.add("分类管理","添加分类","<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");
		
		bar1.add("图书管理","查看图书","<c:url value='/admin/AdminBookServlet?method=findAll'/>", "body");
		bar1.add("图书管理","添加图书","<c:url value='/admin/AdminBookServlet?method=addPre'/>", "body");
		
		bar1.add("订单管理","所有订单","<c:url value='/adminjsps/admin/order/list.jsp'/>", "body");
		bar1.add("订单管理","未付款订单","<c:url value='/adminjsps/admin/order/list.jsp'/>", "body");
		bar1.add("订单管理","已付款订单","<c:url value='/adminjsps/admin/order/list.jsp'/>", "body");
		bar1.add("订单管理","未收货订单","<c:url value='/adminjsps/admin/order/list.jsp'/>", "body");
		bar1.add("订单管理","已完成订单","<c:url value='/adminjsps/admin/order/list.jsp'/>", "body");
		
		var d = document.getElementById("menu");
		d.innerHTML = bar1.toString();
	}		
</script>

</head>
<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
	 <div id="menu">
	 </div>
</body>
</html>