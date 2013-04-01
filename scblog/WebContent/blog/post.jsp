<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章</title>
<%@include file="../include/commonresource.jsp" %>
</head>
<body>
	<%@include file="../include/header.jsp" %>

    <!-- /container -->
    <div id="content">
    	<!-- posts start -->
    	<div id="posts">
    		<div class="full-post">
	    		<h1 class="full-post-title">${article.title }</h1>
	    		<div class="meta-full-post">发布于: 2013-03-27 by <span class="color_orange">Stephen Chen</span></div>
	    		<div class="full-post-content">
	    			<p>
	    				<img src="../images/image4.jpg" alt="Another Image in a Post">
	    			</p>
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
	    				<p><input type="text" class="searchfield" /> 称呼(必填)</p>
	    				<p><input type="text" class="searchfield" /> 邮箱</p>
	    				<p><input type="text" class="searchfield" /> 网站</p>
	    				<p><textarea class="searchfield" cols="60" rows="10"></textarea></p>
	    				<p><a class="btn btn-large">提 交</a></p>
	    			</form>
	    		</div>
    		</div>
		</div>		
		<%@include file="../include/slidebar.jsp" %>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>