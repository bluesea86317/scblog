<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- slidebar start -->
   	<div id="slidebar">
   		<div class="" style="width: 285px; margin-left: 35px;">
    		<div class="search-form">
				<form action="" method="get">
					<input type="text" value="搜索..." name="s" id="ls" class="searchfield" onfocus="if (this.value == '搜索...') {this.value = '';}" onblur="if (this.value == '') {this.value = '搜索...';}">
				</form>
			</div>
			<div class="sidebar-content">				
				<h5>近期文章</h5>
				<ul id="recentArticles">
				</ul>				
				<br>
				<h5>文章分类</h5>
				<ul id="articleTypes">
				</ul>
				<br>
				<h5>人生格言</h5>
				<div>自从我变成了一坨屎,就再也没有人踩在我头上了.</div>
			</div>
		</div>
   	</div>
   	<script type="text/javascript">
	   	function showRecentArticle(){
	   		$.ajax({url: "./ajaxReq.do", type: 'post', data : {action : "listRecentArticles"}, dataType:"json", success: function(result){
				var article_li = "";
				$(result).each(function(i){
					article_li = article_li + '<li><a href="./post.htm?id='+ $(result).get(i).id +'">'+ result[i].title +'</a></li>';
					$("#recentArticles").html(article_li);
				});
			}
	   		});
	   	}
		
	   	function listArticleType(){
			$.ajax({url: "./ajaxReq.do", type: 'post', data : {action : "listArticleTypesWithCount"}, dataType:"json", success: function(result){				
				var articleType_li = "";
				$(result).each(function(i){
					articleType_li = articleType_li + '<li><a href="./?t='+ result[i].id +'">'+ result[i].typeName + '</a> (' + result[i].articleCount + ')</li>'
				});
				$("#articleTypes").html(articleType_li);
			}
			});
		}
	   	
	   
	
	   	$(document).ready(function(){
	   		showRecentArticle();
	   		listArticleType();
	   	});
   	</script>