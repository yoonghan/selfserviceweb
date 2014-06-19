<c:set var="introImages" value="${func:queryImageCategories()}"/>
<html lang="en">
<head>
 <meta charset="UTF-8">
 <title>Jom Jaring - Main</title>
 <link rel="stylesheet" href="${SERVER_LOCATION}/css/idangerous.swiper.css">
 <%@include file="/template/header/common.jsp"%>
 <style>
body {
 margin: 0;
 font-family: Arial, Helvetica, sans-serif;
 font-size: 13px;
 line-height: 1.5;
 color: white;
 background-color: black;
}
.device {
 width: ${DESKTOP_DEFAULT_WIDTH+100}px;
 height: ${DESKTOP_DEFAULT_HEIGHT}px;
 background: black;
 margin: auto;
 position: relative;
}
.device .arrow-left {
 background: url(${SERVER_LOCATION}/img/arws.png) no-repeat left bottom;
 position: absolute;
 left: 10px;
 top: 50%;
 width: 30px;
 height: 60px;
}
.device .arrow-left:hover {
 background: url(${SERVER_LOCATION}/img/arwsh.png) no-repeat right bottom;
 position: absolute;
 top: 50%;
 width: 30px;
 height: 60px;
}
.device .arrow-right {
 background: url(${SERVER_LOCATION}/img/arws.png) no-repeat left top;
 position: absolute;
 right: 10px;
 top: 50%;
 width: 30px;
 height: 60px;
}
.device .arrow-right:hover {
 background: url(${SERVER_LOCATION}/img/arwsh.png) no-repeat right top;
 position: absolute;
 top: 50%;
 width: 30px;
 height: 60px;
}
.swiper-container {
 width: ${DESKTOP_DEFAULT_WIDTH}px;
 height: ${DESKTOP_DEFAULT_HEIGHT}px;
}
.content-slide {
 padding: 0px;
 color: #fff;
}
.title {
 font-size: 25px;
 margin-bottom: 10px;
 font-weight: bold;
}
.pagination {
 position: absolute;
 left: 0;
 text-align: center;
 bottom:5px;
 width: 100%;
}
.swiper-pagination-switch {
 display: inline-block;
 width: 10px;
 height: 10px;
 border-radius: 10px;
 background: #999;
 box-shadow: 0px 1px 2px #555 inset;
 margin: 0 3px;
 cursor: pointer;
}
.swiper-active-switch {
 background: #fff;
}
.title {
 font-style: italic;
}
 </style>
 <c:set var="menu" value="${func:queryMainMenuList()}"/>
<%@include file="/template/header/menu.jsp" %>
<div class="device">
    <a class="arrow-left" href="#"></a> 
    <a class="arrow-right" href="#"></a>
    <div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="content-slide">
					<p class="title">Welcome to JOM JARING</p>
					<img src="${SERVER_LOCATION}/img/mainImage.jpg"/>
				</div>
			</div>	
${introImages}	
		</div>
	</div>
	<div class="pagination"></div>
</div>
  <script src="${SERVER_LOCATION}/js/idangerous.swiper-2.1.min.js"></script>
  <script>
  var mySwiper = new Swiper('.swiper-container',{
    pagination: '.pagination',
    loop:true,
    grabCursor: true,
    paginationClickable: true
  })
  $('.arrow-left').on('click', function(e){
    e.preventDefault()
    mySwiper.swipePrev()
  })
  $('.arrow-right').on('click', function(e){
    e.preventDefault()
    mySwiper.swipeNext()
  })
  </script>
<footnote>
<br/>All images used with this site are copyright reserved to JomJaring&copy; - "Let's Web". 
<br/>To use the images above by any means, your may send your request via email to: mailyoonghan@gmail.com.
</footnote>
<%@include file="/template/footer/revision.jsp" %>
</body>
</html>