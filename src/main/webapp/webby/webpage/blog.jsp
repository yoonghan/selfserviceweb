<html lang="en">
<head>
<title>Site Development - Blog</title>
<%@include file="/template/header/common.jsp"%>
<script type="text/javascript" src="${SERVER_LOCATION}/js/date.js"></script>
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
	$("article").html("");
	display(jsonData);
}

function sortDescDisplay(){
	jsonData.sort(sortDateDesc);
	$("article").html("");
	display(jsonData);
}

function display(data)
{
$.each(data, function(i,data)
{
			var items = [];
			items.push("<dt>"+data.date+"</dt><ts>"+ data.task +"</ts><dl>"+ data.detail + "</dl>");
			
            $("<div/>",{
            	"class":"b",
				html:items.join("")
			}).appendTo('article');
});			
}
</script>
<style>
body{
 font-family: verdana, arial;
 color: #555;
}
div.b{
 margin: 2px;
 padding: 10px;
 border-width: 1px;
 border-style: solid;
 border-radius: 15px;
 background-color: #FDC;
}
div.b:nth-child(even){
 background-color: #CCD;
}
div dt{
 font-size:10pt;
 font-style:italic;
}
div ts{
 font-weight:bold;
}

input[type=checkbox] {
 display:none; visibility:hidden;
}

span{
 font-size: 60px;
 font-weight: bold;
 font-family: 'Source Sans Pro', Helvetica, Arial ,sans-serif;
 right:30px;
 top:40px;
 background-color: #FF2211;
 height:80px;
 width:80px;
 opacity:0.4;
 filter:alpha(opacity=40);
 text-align:center;
 position:fixed;
 border-width: 2px;
 border-style: solid;
 border-radius: 15px;
 box-shadow: inset 0 0 5px #000000;
 box-shadow: 7px 7px 1px 1px #aaa;
}

span:hover{
 opacity:0.8;
 filter:alpha(opacity=80);
}

span:last-child { display:none; visibility:hidden; }
input[type=checkbox]:checked + label span:last-child { display:inline; visibility:visible; }
input[type=checkbox]:checked + label span:first-child { display:none; visibility:hidden; }
</style>

<c:set var="menu" value="${func:querySubMenuList()}"/>
<%@include file="/template/header/menu.jsp" %>

<br/><br/><br/><br/><br/>
<h1>JOM JARING Site Blog</h1>

<input type="checkbox" id="articleshift" value="none">
<label for="articleshift">
	<span onclick="sortAscDisplay();" class="tooltip" data-tip="By Oldest Post">&#8593;</span>
	<span onclick="sortDescDisplay();" class="tooltip" data-tip="By Latest Post">&#8595;</span>
</label>
</input>

<article id="article"></article>
<br>
<a href="#">Back to top</a>
</body>
</html>