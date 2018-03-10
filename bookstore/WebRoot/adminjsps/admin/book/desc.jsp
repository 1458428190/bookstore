<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function setMethod(method)
	{
		var me = document.getElementById("method");
		me.value = method;
	}
</script>
</head>
<body>
	<img src="<c:url value='/${book.image }'/>"/>
	<form action="<c:url value='/admin/AdminBookServlet'/>" method="post">
		<input type="hidden" name="method" id="method" value=""/>
		<input type="hidden" name="bid" value="${book.bid }"/>
		图书名称:<input type="text" name="bname"	 value="${book.bname }"/><br>
		图书单价:<input type="text" name="price" value="${book.price }"/>元<br>
		图书作者:<input type="text" name="author" value="${book.author }"/><br>
		图书分类:
		<select name="cid">
			<c:forEach items="${categoryList }" var="category">
				<option value="${category.cid }" <c:if test="${book.cid eq category.cid }">selected="selected"</c:if> >${category.cname }</option>
			</c:forEach>
		</select>
		<br>
		<input type="submit" value="删除" onclick="setMethod('delete')"/>
		<input type="submit" value="修改" onclick="setMethod('update')"/>
	 </form>
</body>
</html>