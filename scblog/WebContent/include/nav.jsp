<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="span2"> 
  <ul class="nav nav-tabs nav-stacked ">
   	<li ${param.nodeId == 'article_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/articleManage.do">文章管理</a></li>
   	<li ${param.nodeId == 'comment_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/comment_list.jsp">评论管理</a></li>
   	<li ${param.nodeId == 'tag_list' ? 'class=active' :'' }><a href="${web_host }/adminweb/tag_list.jsp">标签管理</a></li>   	
  </ul> 
</div>