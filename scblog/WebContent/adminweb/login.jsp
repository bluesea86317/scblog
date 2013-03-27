<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理登录</title>
<%@include file="../include/commonresource.jsp" %>
<style type="text/css">
	  body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }
	 .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
</style>
</head>
<body>
	<div style="margin: 0 auto;width: 500px;">
	<form class="form-signin" action="./login.do" id="save_form" name="save_form" method="post">
	    <div class="control-group">
			<div style="color:red;">${message}</div>
		    <label class="control-label" for="inputEmail">用户名：</label>
		    <div class="controls">
		    	<input type="text" name="userName" id="userName" class="input-block-level" placeholder="用户名">
		    </div>
	    </div>
	    <div class="control-group">
		    <label class="control-label" for="inputPassword">密码：</label>
		    <div class="controls">
		    	<input type="password" name="userPassword" id="userPassword" class="input-block-level" placeholder="密码">
	    	</div>
	    </div>
	    <div class="control-group">
		    <div class="controls">
		    	<button class="btn btn-large btn-primary" type="submit">登录</button>
		    </div>
	    </div>
    </form>
    </div>
</body>
<%@include file="../include/commonresourceft.jsp"%>
</html>