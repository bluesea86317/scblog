<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论列表</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript">
	function verify(id){
		$.post("comment.do",{action:'update', id : id}, function(data){
			var result = eval("(" + data + ")");
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		});
	}
	
	function deleteComment(id){
		$.post("comment.do",{action:'delete', id : id}, function(data){
			var result = eval("(" + data + ")");
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		});
	}
</script>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div id="content">
		<div class="row-fluid">
			<jsp:include page="../include/nav.jsp">
				<jsp:param value="comment_list" name="nodeId"/>
			</jsp:include>
			<div class="span10">
				<table class="table table-bordered" width="100%">
				  <tr>
					<th width="5%"></th>
					<th width="10%">编号</th>
					<th width="40%">评论内容</th>
					<th>文章编号</th>
					<th>评论时间</th>
					<th>状态</th>
					<th>操作</th>
				  </tr>
				  <c:forEach items="${comments }" var="comment">
				  <tr>
				  	<td><input type="checkbox"/></td>
				  	<td>${comment.id }</td>
				  	<td>${comment.comment }</td>
				  	<td>${comment.articleId }</td>
				  	<td><fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				  	<td>${comment.status }</td>
				  	<td><a href="javascript:verify('${comment.id }');">审核通过</a>  <a href="javascript:deleteComment('${comment.id }');">删除</a></td>
				  </tr>
				  </c:forEach>
				  </table>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>