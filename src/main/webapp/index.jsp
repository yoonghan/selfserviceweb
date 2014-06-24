<c:set var="introImages" value="${func:queryImageCategories()}"/>
<html lang="en">
<head>
<title>Jom Jaring</title>
<link href='http://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${SERVER_LOCATION}/js/jquery.js"></script>
<script type="text/javascript" src="${SERVER_LOCATION}/js/jquery.vegas.min.js"></script>
<link rel="stylesheet" type="text/css" href="${SERVER_LOCATION}/css/vegas.min.css" />
<script>
var imgAry = new Array( ${introImages.imageNameAsJS} );
var fadeTime = 6000;
$(function() {


  $.vegas( 'slideshow', {
	backgrounds: [
		${introImages.imageSrcAsJS}
	]
  })('overlay');
  
  $('body').bind('vegaswalk',
    function(e, bg, step) {
		$("note").text("-"+imgAry[ step ]+"-");
    }
  );
});

</script>
<style>
note {
 font-family: 'Rock Salt', cursive;
 font-size: 15pt;
 font-style: bold;
 position:absolute;
 bottom:-10px;
 background-color:#DDD;
 opacity:0.8;
 filter:alpha(opacity=80);
 margin: 1px;
 padding: 2px;
 border-radius: 2px;
 color: black;
 z-index: 100;
}
footnote {
 font-size: 8pt;
 position:absolute;
 bottom:0px;
 left:0px;
 background-color:#555;
 opacity:0.5;
 filter:alpha(opacity=50);
 width: 100%;
 color: white;
 z-index: 100;
}
</style>
</head> 	
<body>
<note>Loading.</note>
<footnote>
 To use the images in this site, please request from the author: mailyoonghan@gmail.com.
</footnote>
</body>
</html>