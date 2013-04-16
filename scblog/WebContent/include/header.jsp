<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-fixed-top" id="header">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="${web_host }/blog/post.htm">SC'BL<span id="breath_o" style="opacity: 0;">O</span>G</a>
          <div class="nav-collapse pull-right">
            <ul class="nav">
              <li class="active"><a href="#">主页</a></li>
              <li><a href="#about">关于我</a></li>
              <li><a href="#contact">联系我</a></li>
            </ul>
            <c:if test="${!empty logonUser }">
            	<a href="./add_article.jsp">写文章</a> | <a href="${web_host }/adminweb/logout.do">退 出</a>
            </c:if>
          </div><!--/.nav-collapse -->
        </div>
      </div>
</div>
