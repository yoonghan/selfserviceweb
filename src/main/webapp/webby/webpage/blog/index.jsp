<html lang="en">
<head>
<title>Site Development - Blog</title>
<link rel="stylesheet" href="${SERVER_LOCATION}/cache/css/common.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/submenu.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/ssblog.css">
<script type="text/javascript" src="${SERVER_LOCATION}/js/date.js"></script>
<script type="text/javascript" src="${SERVER_LOCATION}/js/jquery.js"></script>
<script>
$( document ).ready(function() {
 $.get("../../../rest/html/submenu", function( data )
 {
	 $("menu").html(data);
	 $("#menu").before(data);
 });
});
</script>
<script type="text/javascript">
var jsonData;
$(function() 
{
$(document).ready(function()
{
$.getJSON("${SERVER_LOCATION}/json/blog.json",function(data)
{
	jsonData = data.posts;
	display(jsonData);
});
return false;
});
});

function sortDateAsc(a, b) {  
    return Date.parseExact(a.date, "MMM d, yyyy").getTime() <  Date.parseExact(b.date, "MMM d, yyyy").getTime();  
} 

function sortDateDesc(a, b) {  
    return Date.parseExact(b.date, "MMM d, yyyy").getTime() <  Date.parseExact(a.date, "MMM d, yyyy").getTime();  
} 

function sortAscDisplay(){
	jsonData.sort(sortDateAsc);
	$("#article").html("");
	display(jsonData);
}

function sortDescDisplay(){
	jsonData.sort(sortDateDesc);
	$("#article").html("");
	display(jsonData);
}

function display(data)
{
$.each(data, function(i,data)
{
			var items = [];
			items.push("<dt>"+data.date+"</dt><dl><ts>"+ data.task +"</ts><dtl>"+ data.detail + "</dtl></dl>");
			
            $("<div/>",{
            	"class":"row",
				html:items.join("")
			}).appendTo('#article');
});			
}
</script>
</head>
<body>
	<menu>
	</menu>

<img src="${SERVER_LOCATION}/img/blogbanner.jpg" style="-ms-interpolation-mode:bicubic;image-rendering:optimizeQuality;width:100%;"></img>
	
<div class="fullbar" id="T2">
 <h1>Site Blog</h1>
 <h3>Marks the changes and progress</h3>
</div>

<input type="checkbox" id="articleshift" value="none">
<label for="articleshift">
	<span onclick="sortAscDisplay();" class="tooltip" data-tip="By Oldest Post">&#8593;</span>
	<span onclick="sortDescDisplay();" class="tooltip" data-tip="By Latest Post">&#8595;</span>
</label>
</input>

<div id="article"></div>
<p class="addMargin">
<a href="#">Back to top</a>
</p>
<div class="footnote">
<br id="menu">
&copy;2014 All rights reserved - JOM Jaring<br/>
If you are interested, do not hesitate to contact us.
</div>
</body>
</html>