<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar navbar-fixed-top" id="header">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="${web_host }/">SC'BL<span id="breath_o" style="opacity: 0;">O</span>G</a>
          <div class="nav-collapse pull-right">
            <ul class="nav">
              <li ${empty param.index ? 'class=active':'' }><a href="${web_host }/">主页</a></li>
              <li ${param.index == 'about'? 'class=active':'' }><a href="${web_host }/about.jsp">关于我</a></li>
              <li><a href="http://weibo.com/iamstephenchen" target="_blank">关注我</a></li>
            </ul>
            
          </div><!--/.nav-collapse -->
        </div>
      </div>
</div>
