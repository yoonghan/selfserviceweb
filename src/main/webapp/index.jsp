<html lang="en">
<head>
<!-- MOBIFY - DO NOT ALTER - PASTE IMMEDIATELY AFTER OPENING HEAD TAG -->
<script type="text/javascript">/*<![CDATA[*/(function(e,f){function h(a){if(a.mode){var b=g("mobify-mode");b&&a[b]||(b=a.mode(c.ua));return a[b]}return a}function m(){function a(a){e.addEventListener(a,function(){c[a]=+new Date},!1)}e.addEventListener&&(a("DOMContentLoaded"),a("load"))}function n(){var a=new Date;a.setTime(a.getTime()+3E5);f.cookie="mobify-path=; expires="+a.toGMTString()+"; path=/";e.location.reload()}function p(){k({src:"https://preview.mobify.com/v7/"})}function g(a){if(a=f.cookie.match(RegExp("(^|; )"+a+"((=([^;]*))|(; |$))")))return a[4]||""}function l(a){f.write('<plaintext style="display:none">');setTimeout(function(){d.capturing=!0;a()},0)}function k(a,b){var e=f.getElementsByTagName("script")[0],c=f.createElement("script"),d;for(d in a)c[d]=a[d];b&&c.setAttribute("class",b);e.parentNode.insertBefore(c,e)}var d=e.Mobify={},c=d.Tag={};d.points=[+new Date];d.tagVersion=[7,0];c.ua=e.navigator.userAgent;c.getOptions=h;c.init=function(a){c.options=a;if(""!==g("mobify-path"))if(m(),a.skipPreview||"true"!=g("mobify-path")&&!/mobify-path=true/.test(e.location.hash)){var b=h(a);if(b){var d=function(){b.post&&b.post()};a=function(){b.pre&&b.pre();k({id:"mobify-js",src:b.url,onerror:n,onload:d},"mobify")};!1===b.capture?a():l(a)}}else l(p)}})(window,document);(function(){var o="//cdn.mobify.com/swift/jomjaring/production/mobify.js";Mobify.Tag.init({mode:function(o){return/ip(hone|od|ad)|android|blackberry.*applewebkit|bb1\d.*mobile/i.test(o)?"enabled":"desktop"},enabled:{url:o},disabled:{capture:!1,url:o},desktop:{capture:!1,url:"//a.mobify.com/performance/jomjaring/a.js"}})})();/*]]>*/</script>
<!-- END MOBIFY -->

<title>JOM Jaring Introductory Page</title>
<meta name="description" content="A basic company setup by Yoong Han and Lee Wan, used as a profile page.">
<link rel="author" href="https://plus.google.com/u/1/114852108498604797792"/>

<meta property="og:title" content="JOM Jaring"/>
<meta property="og:type" content="profile"/>
<meta property="og:image" content="${SERVER_LOCATION}/img/jomjaring.jpg"/>
<meta property="og:description" content="An awesome site that introduce the capabilities we have."/>
<meta property="fb:admins" content="jomjaring@gmail.com"/>

<link href='http://fonts.googleapis.com/css?family=Rock+Salt' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="${SERVER_LOCATION}/js/jquery.js"></script>
<script type="text/javascript" src="${SERVER_LOCATION}/js/jquery.vegas.min.js"></script>
<link rel="stylesheet" type="text/css" href="${SERVER_LOCATION}/css/vegas.min.css" />
<link rel="stylesheet" type="text/css" href="${SERVER_LOCATION}/css/webby/ssmain.css" />
<script>
$(function() {
 $.getJSON("./rest/html/introImages",function(data)
 {
	$.vegas( 'slideshow', data)('overlay');			
 }); 
});
$( document ).ready(function() {
 $('#show').on('click', function() {
  $('#nav').css("visibility","visible");
 });
 $('#hide').on('click', function() {
  $('#nav').css("visibility","hidden");
 });
 $('#nav_listener').on('click', function() {
  $('#nav').css("visibility","hidden");
 });
 
 $.get("./rest/html/mainmenu", function( data )
 {
	 $("#menuList").before(data);
 });
 $.get("./rest/html/introBtn", function( data )
 {
	 $("#show").before(data);
 });
});

</script>
</head>
<body>
<div id="nav_listener" style="min-width:96%;min-height:96%;z-index:96;position:absolute;"></div>

<button type="button" id="show" class="button"><img src="${SERVER_LOCATION}/img/nav.svg"></button>

<div class="menupanel" id="nav"><hr/><hr/>
 <nav>
  <hr id="menuList"/>
  <button type="button" id="hide" class="button_hide">&lt;&lt; Hide</button>
 <nav>
</div>
<footnote>
 To use the images in this site, please request from the author: mailyoonghan@gmail.com.
</footnote>
</body>
</html>