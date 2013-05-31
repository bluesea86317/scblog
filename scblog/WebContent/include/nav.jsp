<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="span2">
  <ul class="nav nav-tabs nav-stacked ">
   	<li ${param.nodeId == 'article_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/articleManage.do">文章管理</a></li>
   	<li ${param.nodeId == 'comment_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/comment.do">评论管理</a></li>
   	<li ${param.nodeId == 'tag_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/tagManage.do">标签管理</a></li>
   	<li ${param.nodeId == 'article_type_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/articleType.do">文章类型管理</a></li>
  </ul> 
  <c:if test="${!empty logonUser }">
  	<a href="./add_article.jsp">写文章</a> | <a href="${web_host }/adminweb/logout.do">退 出</a>
  </c:if>
</div>