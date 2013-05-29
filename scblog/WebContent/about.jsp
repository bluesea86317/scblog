<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Stephen Chen 个人博客</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A blog about software development technology">
    <meta name="author" content="Stephen Chen">
	<%@include file="./include/commonresource.jsp" %>
	<script type="text/javascript">
		$(document).ready(
			function(){
				$('#myCarousel').carousel();
			}
		);
	</script>
  </head>
  <body>

    <%--@include file="./include/header.jsp" --%>
    <jsp:include page="./include/header.jsp">
    	<jsp:param value="about" name="index"/>
    </jsp:include>

    <!-- /container -->
    <div id="main">
    	<div class="welcome-board pull-left">
    		
    	</div>
    	<div class="pic-slide pull-right">
	        <!-- <div id="myCarousel" class="carousel slide">
		    Carousel items
			    <div class="carousel-inner">
				    <div class="active item">
				    	<img alt="pic1" src="./images/IMG_1392.JPG">
					</div>
				    <div class="item">
				    	<img alt="pic2" src="./images/IMAG0124.jpg">
				    </div>
				    <div class="item">
						<img alt="pic3" src="./images/IMG_1405.JPG">
					</div>				
			    </div>
			    Carousel nav
			    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
		    </div> -->
		    <!-- <h1>Bootstrap starter template</h1>-->
      		<p>深圳IT从业者，热爱软件开发，热爱游戏，热爱互联网，也热爱生活。</p>
    		<a class="btn btn-success btn-large" href="${web_host }/"><span>Welcome, to my blog >></span></a>
	    </div>
      
    </div> 
	<%@include file="./include/footer.jsp" %>
  </body>
</html>