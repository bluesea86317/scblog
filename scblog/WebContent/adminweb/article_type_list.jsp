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
	function addType(){
		var typeName = $("#articleTypeName").val();
		$.post("articleType.do",{action:'add', typeName : typeName}, function(data){
			var result = eval("(" + data + ")");
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		});
	}

	function showUpdate(id, typaName){
			$("#add_btn").hide();
			$("#update_btn").show();
			$("#articleTypeName").val(typaName);
			$("#typeId").val(id);
	}
	
	function updateType(){
		var typeName = $("#articleTypeName").val();
		var id = $("#typeId").val();
		$.post("articleType.do",{action:'update', typeId : id, typeName : typeName}, function(data){
			var result = eval("(" + data + ")");
			if(result.resultCode == "success"){
				window.alert(result.msg, true);				
			}else{
				window.alert(result.msg, false);
			}
			
		});
	}
	
	function deleteType(id){
		$.post("articleType.do",{action:'delete', typeId : id}, function(data){
			var result = eval("(" + data + ")");
			if(result.resultCode == "success"){
				window.alert(result.msg, true);
			}else{
				window.alert(result.msg, false);
			}
			
		});
	}
</script>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div id="content">
		<div class="row-fluid">
			<jsp:include page="../include/nav.jsp">
				<jsp:param value="article_type_list" name="nodeId"/>
			</jsp:include>
			<div class="span10">
				<div class="controls">
	              <div class="input-append">
	              	<input type="hidden" id="typeId" value="">
	                <input id="articleTypeName" size="16" type="text" placeholder="类型名称"><button class="btn" type="button" onclick="addType()" id="add_btn">新增</button><button class="btn" type="button" onclick="updateType()" style="display: none" id="update_btn">更新</button>
	              </div>
	            </div>
				<table class="table table-bordered" width="100%">
				  <tr>
					<th width="5%"></th>
					<th width="10%">编号</th>
					<th width="40%">类型名称</th>					
					<th>操作</th>
				  </tr>
				  <c:forEach items="${articleTypeList }" var="articleType">
				  <tr>
				  	<td><input type="checkbox"/></td>
				  	<td>${articleType.id }</td>
				  	<td>${articleType.typeName }</td>				  	
				  	<td><a href="javascript:showUpdate('${articleType.id }','${articleType.typeName }');">修改</a>  <a href="javascript:deleteType('${articleType.id }');">删除</a></td>
				  </tr>
				  </c:forEach>
				  </table>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>