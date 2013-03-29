<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>
<%@include file="../include/commonresource.jsp" %>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div id="content">
		<div class="row-fluid">
		<jsp:include page="../include/nav.jsp">
			<jsp:param value="article_list" name="nodeId"/>
		</jsp:include>
		<div class="span10">
			<a class="btn btn-success" href="./add_article.jsp">写 文 章</a>
			<table class="table table-bordered" width="100%">
			  <tr>
				<th width="10%"></th>
				<th width="9%">编号</th>
				<th width="45%">文章标题</th>
				<th width="15%">发布时间</th>
				<th>操作</th>
			  </tr>
			  <c:forEach items="${articles }" var="article">			  
				  <tr>
				  	<td><input type="checkbox"/></td>
				  	<td>${article.id }</td>
				  	<td>${article.title }</td>
				  	<td>${article.createTime }</td>
				  	<td><a href="./edit_article.jsp">修改</a> 删除</td>
				  </tr>
			  </c:forEach>
			  </table>
		</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %> 
</body>
</html>