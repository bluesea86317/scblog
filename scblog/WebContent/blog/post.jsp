<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript">
	function submitComment(){
		var articleId = $("#articleId").val();
		var visitor = $("#visitor").val();
		var email = $("#email").val();
		var commentContent = $("#commentContent").val();
		var website = $("#website").val();
		
		$.post("./comment.do",{
			action:'add',
			articleId:articleId,
			visitor:visitor,
			email:email,
			commentContent:commentContent,
			website:website
		}, function(date){
			var result = eval("("+date+")");
			if(result.resultCode == "success"){
				window.alert(result.msg,true);				
			}else{
				window.alert(result.msg,false);
			}
		});
	}
	
	function showComment(){
		$.post("./comment.do",{
			action:'showComment',
			articleId:'${article.id}'
		}, function(date){
			var result = eval("("+date+")");
			alert(result);
		});
	}
	
	window.onload = function(){
		showComment();
	};
</script>
</head>
<body>
	<%@include file="../include/header.jsp" %>

    <!-- /container -->
    <div id="content">
    	<!-- posts start -->
    	<div id="posts">
    		<div class="full-post">
	    		<h1 class="full-post-title">${article.title }</h1>
	    		<div class="meta-full-post">发布于: <fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> by <span class="color_orange">Stephen Chen</span></div>
	    		<div class="full-post-content">
	    			<!-- <p>
	    				<img src="../images/image4.jpg" alt="Another Image in a Post">
	    			</p> -->
	    			<div>
	    				 ${article.content }
	    			</div>
	    		</div>
	    		<div class="meta-full-post">
	    			<p>分类: <span class="color_orange">图文</span></p>
	    			<p>标签:
	    				<c:forEach items="${article.tags}" var="tag">
	    					<span class="color_orange">${tag.tagName }</span>
	    				</c:forEach>
	    		</div>
	    		
	    		<div id="respond">
	    			<h3 class="postcomment">发表评论</h3>
	    			<form action="" method="post">
	    				<input type="hidden" name="articleId" id="articleId" value="${article.id}">
	    				<p><input type="text" class="searchfield" id="visitor" name="visitor"/> 称呼(必填)</p>
	    				<p><input type="text" class="searchfield" id="email" name="email"/> 邮箱</p>
	    				<p><input type="text" class="searchfield" id="website" name="website"/> 网站</p>
	    				<p><textarea class="searchfield" cols="60" rows="10" id="commentContent" name="commentContent"></textarea></p>
	    				<p><a class="btn btn-large" href="javascript:submitComment();">提 交</a></p>
	    			</form>
	    		</div>
    		</div>
		</div>		
		<%@include file="../include/slidebar.jsp" %>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>