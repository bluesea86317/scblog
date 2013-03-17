<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>就这么飘来荡去</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<%@include file="./include/commonresource.jsp" %>
	<script type="text/javascript">
		$(document).ready(
			function(){
				$('#myCarousel').carousel();
			}
		);
	</script>

  <body>

    <%@include file="./include/header.jsp" %>

    <!-- /container -->
    <div class="container">
    	<div style="width:600px;">
	        <div id="myCarousel" class="carousel slide">
		    <!-- Carousel items -->
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
			    <!-- Carousel nav -->
			    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
		    </div>
	    </div>
      <h1>Bootstrap starter template</h1>
      <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>
    </div> 
	<%@include file="./include/footer.jsp" %>
  </body>
</html>