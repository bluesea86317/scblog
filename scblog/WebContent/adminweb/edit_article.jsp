<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文章</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript" src="./fckeditor/fckeditor.js"></script>
<script type="text/javascript">
	window.onload = function()
	{
		var oFCKeditor = new FCKeditor('mailContent') ;
		oFCKeditor.BasePath = "./fckeditor/";
		oFCKeditor.Height = 300 ;       
		oFCKeditor.Width = 600;    
		oFCKeditor.ToolbarSet = 'Basic';
		oFCKeditor.ReplaceTextarea() ;
	};
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
				<table class="table table-bordered">
				<c:if test="${!empty mailTemplate }">
					<tr>
						<th>序号：</th>
						<td></td>
					</tr>
				</c:if>
				<tr>
					<th>文章标题：</th>
					<td><input type="text" name="mailTitle" id="mailTitle" value="" size="70"/></td>
				</tr>
				<tr>
					<th>标签：</th>
					<td>
						<input type="text" name="bccMail" id="bccMail" value="" size="70" maxlength="150"/>
						多个标签之间, 请用";"号隔开
					</td>
				</tr>
				<tr>
					<th>*文章内容：</th>
					<td>
						<div id="box-body">&nbsp;&nbsp;<textarea style="display: none;" id="mailContent" name="mailContent" cols="" rows="20" class="textarea">${mailTemplate.mailContent}</textarea></div>
					</td>
				</tr>
			</table>
			<div class="text-center">
				<a class="btn btn-success">保 存</a>
				<a class="btn">返 回</a>
			</div>	
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>