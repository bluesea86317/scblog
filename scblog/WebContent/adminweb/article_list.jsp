<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript">
	
	function deleteArticle(id){
		$.post("articleManage.do",{action:'delete', articleId : id}, function(data){
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
			<jsp:param value="article_list" name="nodeId"/>
		</jsp:include>
		<div class="span10">
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
				  	<td><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				  	<td><a href="./edit_article.jsp">修改</a><a href="javascript:deleteArticle('${article.id }');">删除</a></td>
				  </tr>
			  </c:forEach>
			  </table>
		</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>

</body>
</html>