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
					<!-- <li>今天下小雨</li>
					<li>明天下大雨</li>
					<li>为什么要一直下雨,真是搞不懂这个疯狂滴世界</li>
					<li>后天下暴雨</li>
					<li>好吧,我屈服了, Mother Fucker!</li> -->
				</ul>
				<br>
				<h5>人生格言</h5>
				<div>自从我变成了一坨屎,就再也没有人踩在我头上了.自从我变成了一坨屎,就再也没有人踩在我头上了.自从我变成了一坨屎,就再也没有人踩在我头上了.自从我变成了一坨屎,就再也没有人踩在我头上了.</div>
				<br>
				<h5>文章分类</h5>
				<ul>
					<li>前端</li>
					<li>Java</li>
					<li>Javascript</li>
					<li>生活</li>
					<li>其他</li>
				</ul>
			</div>
		</div>
   	</div>
   	<script type="text/javascript">
	   	function showRecentArticle(){
	   		$.post("./post.do",{
				action:'listRecentArticles'
			}, function(date){
				var result = eval("("+date+")");
				var article_li = "";
				$(result).each(function(i){
					article_li = article_li + '<li>'+ result[i].title +'</li>';
					$("#recentArticles").html(article_li);
				});
			});
	   	}
	
	   	function showArticleType(){
	   		alert(2);
	   	}
	
	   	$(document).ready(function(){
	   		showRecentArticle();
	   		showArticleType();
	   	});
   	</script>