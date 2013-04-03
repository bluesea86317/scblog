<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博文 SC'BLOG</title>
<%@include file="../include/commonresource.jsp" %>
</head>
<body>
	<%@include file="../include/header.jsp" %>

    <!-- /container -->
    <div id="content">
    	<!-- posts start -->
    	<div id="posts">
    		<c:forEach items="${articles }" var="article">
    		<div class="single-post">
    			<!-- <div class="single-post-image">
    				<img src="../images/image5.jpg" alt="Another Image in a Post" style="opacity: 1;">
    			</div> -->
    			<div class="single-post-text">
    				<h2><a href="./post.htm?id=${article.id }">${article.title }</a></h2>
    				<div class="single-post-content pull-left">
    					${article.intro }
					</div>
					<div class="meta pull-left">
						<p><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						<p>Stephen Chen</p>
					</div>
					<div class="clearfix"></div>
    			</div>
    		</div>
    		</c:forEach>
    		<!-- <div class="single-post">
    			<div class="single-post-image">
    				<img src="../images/image10.jpg" alt="Another Image in a Post" style="opacity: 1;">
    			</div>
    			<div class="single-post-text">
    				<h2>这个世界好疯狂</h2>
    				<div class="single-post-content pull-left">
						这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,...
					</div>
					<div class="meta pull-left">
						<p>2013-03-22 16:39</p>
						<p>Stephen Chen</p>
					</div>
					<div class="clearfix"></div>
    			</div>
    		</div>
    		
    		<div class="single-post">
    			<div class="single-post-image">
    				<img src="../images/image4.jpg" alt="Another Image in a Post" style="opacity: 1;">
    			</div>
    			<div class="single-post-text">
    				<h2>这个世界好疯狂</h2>
    				<div class="single-post-content pull-left">
						这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,这个世界好疯狂,个世界好疯狂,世界好疯狂,界好疯狂,好疯狂,疯狂,狂,...
					</div>
					<div class="meta pull-left">
						<p>2013-03-22 16:39</p>
						<p>Stephen Chen</p>
					</div>
					<div class="clearfix"></div>
    			</div>
    		</div> -->
    	</div>    	
    	<%@include file="../include/slidebar.jsp" %>
    </div>
    <%@include file="../include/footer.jsp" %>
</body>
</html>