<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标签管理</title>
<%@include file="../include/commonresource.jsp" %>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div id="content">
		<div class="row-fluid">
			<jsp:include page="../include/nav.jsp">
				<jsp:param value="tag_list" name="nodeId"/>
			</jsp:include>
			<div class="span10">
				<table class="table table-bordered" width="100%">
				  <tr>
					<th width="10%"></th>
					<th width="9%">编号</th>
					<th width="45%">标签内容</th>
					<th>操作</th>
				  </tr>
				  <tr>
				  	<td><input type="checkbox"/></td>
				  	<td>001</td>
				  	<td>世界</td>
				  	<td>修改 删除</td>
				  </tr>
				</table>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>