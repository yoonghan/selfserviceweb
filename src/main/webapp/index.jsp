<c:set var="introImages" value="${func:queryImageCategories()}"/>

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Jom Jaring - Main</title>
  <link rel="stylesheet" href="webby/css/idangerous.swiper.css">
  <style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 13px;
  line-height: 1.5;
}
.device {
  width: ${DESKTOP_DEFAULT_WIDTH+100}px;
  height: ${DESKTOP_DEFAULT_HEIGHT}px;
  background: black;
  margin: auto;
  position: relative;
}
.device .arrow-left {
  background: url(webby/img/arws.png) no-repeat left bottom;
  position: absolute;
  left: 10px;
  top: 50%;
  width: 30px;
  height: 60px;
}
.device .arrow-left:hover {
  background: url(webby/img/arwsh.png) no-repeat right bottom;
  position: absolute;
  top: 50%;
  width: 30px;
  height: 60px;
}
.device .arrow-right {
  background: url(webby/img/arws.png) no-repeat left top;
  position: absolute;
  right: 10px;
  top: 50%;
  width: 30px;
  height: 60px;
}
.device .arrow-right:hover {
  background: url(webby/img/arwsh.png) no-repeat right top;
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
  <style>
 .headSeperator {
   margin-top:10px;
 }
 .footNote {
   position:absolute;
   bottom:0px;
   background-color:#AAA;
   opacity:0.7;
   filter:alpha(opacity=70);
   margin: 2px;
   padding: 5px;
   width: 97%;
   border-radius: 5px;
   color: white;
   z-index: 100;
 }
  </style>
</head>
<body bgcolor="black">
<div class="headNote">&nbsp;</div>
<div class="device">
    <a class="arrow-left" href="#"></a> 
    <a class="arrow-right" href="#"></a>
    <div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="content-slide">
					<p class="title">Welcome to JOM JARING</p>
					<img src="webby/img/mainImage.jpg"/>
				</div>
			</div>	
			
			<c:forEach var="image" items="${introImages}">
				<div class="swiper-slide"><img src="${IMAGE_LOCATION}${image.URI}"></div>
			</c:forEach>
				
		</div>
	</div>
	<div class="pagination"></div>
</div>
  <script src="webby/js/jquery-1.10.1.min.js"></script>
  <script src="webby/js/idangerous.swiper-2.1.min.js"></script>
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
<div class="footNote">
All images used with this site are copyright reserved to JomJaring&copy; - "Let's Web". 
<br/>
To use the images above by any means, your may send your request via email to: mailyoonghan@gmail.com.
</div>
</body>
</html>