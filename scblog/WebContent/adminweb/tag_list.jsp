<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章类型列表</title>
<%@include file="../include/commonresource.jsp" %>
<script type="text/javascript">
	function addTag(){
		var tagName = $("#tagName").val();
		$.post("tagManage.do",{action:'add', tagName : tagName}, function(result){
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		},'json');
	}

	function showUpdate(id, tagName){
			$("#add_btn").hide();
			$("#update_btn").show();
			$("#tagName").val(tagName);
			$("#tagId").val(id);
	}
	
	function updateTag(){
		var tagName = $("#tagName").val();
		var id = $("#tagId").val();
		$.post("tagManage.do",{action:'update', tagId : id, tagName : tagName}, function(result){
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		},'json');
	}
	
	function deleteTag(id){
		$.post("tagManage.do",{action:'delete', tagId : id}, function(result){
			if(result.resultCode == "success"){
				window.alert(result.msg, true);
			}else{
				window.alert(result.msg, false);
			}
			
		},'json');
	}
</script>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div id="content">
		<div class="row-fluid">
			<jsp:include page="../include/nav.jsp">
				<jsp:param value="tag_list" name="nodeId"/>
			</jsp:include>
			<div class="span10">
				<div class="controls">
	              <div class="input-append">
	              	<input type="hidden" id="tagId" value="">
	                <input id="tagName" size="16" type="text" placeholder="标签名称"><button class="btn" type="button" onclick="addTag()" id="add_btn">新增</button><button class="btn" type="button" onclick="updateTag()" style="display: none" id="update_btn">更新</button>
	              </div>
	            </div>
				<table class="table table-bordered" width="100%">
				  <tr>
					<th width="5%"></th>
					<th width="10%">编号</th>
					<th width="40%">标签名称</th>					
					<th>操作</th>
				  </tr>
				  <c:forEach items="${tagList }" var="tag">
				  <tr>
				  	<td><input type="checkbox"/></td>
				  	<td>${tag.id }</td>
				  	<td>${tag.tagName }</td>				  	
				  	<td><a href="javascript:showUpdate('${tag.id }','${tag.tagName }');">修改</a>  <a href="javascript:deleteTag('${tag.id }');">删除</a></td>
				  </tr>
				  </c:forEach>
				  </table>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>