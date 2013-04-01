<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增文章</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript" src="./fckeditor/fckeditor.js"></script>
<script type="text/javascript">
	window.onload = function()
	{
		var oFCKeditor = new FCKeditor('articleContent') ;
		oFCKeditor.BasePath = "./fckeditor/";
		oFCKeditor.Height = 300 ;       
		oFCKeditor.Width = 600;    
		oFCKeditor.ToolbarSet = 'Basic';
		oFCKeditor.ReplaceTextarea() ;
		
		var oFCKeditor1 = new FCKeditor('articleIntro') ;
		oFCKeditor1.BasePath = "./fckeditor/";
		oFCKeditor1.Height = 300 ;       
		oFCKeditor1.Width = 600;    
		oFCKeditor1.ToolbarSet = 'Basic';
		oFCKeditor1.ReplaceTextarea() ;
	};
	
	function addTemplate(){
		 ajaxPost();
	}
	
	function ajaxPost(){
		var oEditor = FCKeditorAPI.GetInstance("articleContent");
		var oEditor1 = FCKeditorAPI.GetInstance("articleIntro");
		var title = $("#title").val();
		var tag = $("#tag").val();
		var articleContent = oEditor.GetXHTML(true);
		var articleIntro = oEditor1.GetXHTML(true);
		if(title == ""){
			window.alert("文章标题不能为空!");
			return;
		}
		if(articleContent == ""){
			window.alert("文章内容不能为空!");
			return;
		}
		if(articleIntro == ""){
			window.alert("文章内容简介不能为空!");
			return;
		}
		$.post("./articleManage.do", {
			action : "add",
			title:title,
			articleContent: articleContent,
			articleIntro:articleIntro,
			tag:tag
		},
		function (result) {
			window.alert(result);
			window.close();
			window.opener.location.reload();
		}
		);
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
				<table class="table table-bordered">
				<tr>
					<th>文章标题：</th>
					<td><input type="text" name="title" id="title" value="" size="70"/></td>
				</tr>
				<tr>
					<th>内容简介:</th>
					<td><div id="box-body">&nbsp;&nbsp;<textarea style="display: none;" id="articleIntro" name="articleIntro" cols="" rows="20" class="textarea"></textarea></div></td>
				</tr>
				<tr>
					<th>*文章内容：</th>
					<td>
						<div id="box-body">&nbsp;&nbsp;<textarea style="display: none;" id="articleContent" name="articleContent" cols="" rows="20" class="textarea"></textarea></div>
					</td>
				</tr>
				<tr>
					<th>标签：</th>
					<td>
						<input type="text" name="tag" id="tag" value="" size="70" maxlength="150"/>
						多个标签之间, 请用";"号隔开
					</td>
				</tr>
			</table>
			<div class="text-center">
				<a class="btn btn-success" onclick="javascript:addTemplate();">保 存</a>
				<a class="btn">返 回</a>
			</div>	
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>