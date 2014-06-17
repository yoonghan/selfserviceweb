<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Architecture & Technologies</title>

<link rel="stylesheet" href="../css/tablestack/dialog.css">
<link rel="stylesheet" href="../css/tablestack/tablesaw.stackonly.css">
<script src="../js/jquery.js"></script>
<script src="../js/tablestack/dialog.js"></script>
<script src="../js/tablestack/tablesaw.stackonly.js"></script>
<style>
body{
 margin-left:2px;
 font-family: Arial, Helvetica, sans-serif;
 font-size: 19px;
}
.tablesaw td,
.tablesaw tbody th{
 vertical-align: middle;
}
.tablesaw thead{
 border: 1px solid #e5e5e4;
 background: #e2dfdc;
 background-image: -webkit-gradient(linear, left top, left bottom, from(#fff), to(#e2dfdc));
 background-image: -webkit-linear-gradient(top, #fff, #e2dfdc);
 background-image: linear-gradient(to bottom, #fff, #e2dfdc);
}
.tablesaw thead th{
 font-weight: bold;
 color: #777;
 text-transform: uppercase;
 text-shadow: 0 1px 0 #fff;
 text-align: left;
}
.tablesaw-cell-label{
 font-weight:bold;
}
.ul{
 border: 1px solid #e5e5e4;
 background: #ffffff;
}
.content{
 padding:3px;
 font-weight:180;
 color:black;
}
.artImgDisplay{
 display:hide;
}
.title {
font-weight: bold;
position: relative;
background-color: #F33;
margin:2px;
padding:5px;
width: 100%;
color: white;
}
.desc{
 font-style:italic;
}
version{
 margin:2px;
 font-size:9pt;
}
tr:nth-child(even){ background-color:#eee; }

input[type=checkbox] {
    border: 0;
    clip: rect(0 0 0 0);
    height: 1px;
    width: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
}

input[type=checkbox]:checked ~ section { display: block; visibility:visible; }
section {display: none; visibility:hidden;}
</style>

<%@include file="/template/header/submenu.jsp" %>

<br/><br/><br/><br/><br/>
<div class="content">Content</div>
<ol>
 <li><a href="#">Architecture</a></li>
 <li><a href="#T1">Technology</a></li>
 <li><a href="#T2">Project download</a></li>
</ol>

<input type="checkbox" id="art_img" value="none">
<div class="title">
<label for="art_img"><span>+ ARCHITECTURE (click to reveal)</span></label>
</div>
&nbsp;<a href="."><label for="art_img">+Click to show image</label></a>
<br/>
<section>
<img src="../img/architecture.jpg" style="margin-left:2px"/>
</section>


<p/>
<hr>
<div class="title"><a id="T1">TECHNOLOGY</a></div>
<table class="tablesaw" data-mode="stack">
	<thead>
		<tr>
			<th>Technology</th>
			<th>Description</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>JDBI</td>
			<td class="desc">SQL convenience library for Java.</td>
		</tr>
		<tr>
			<td>Openshift</td>
			<td class="desc">Cloud computing PAAS product from Red Hat.</td>
		</tr>
		<tr>
			<td>Jboss</td>
			<td class="desc">Webserver under Java 7 with J2EE 6.</td>
		</tr>
		<tr>
			<td>Tomcat</td>
			<td class="desc">Webserver under Java 7.</td>
		</tr>
		<tr>
			<td>MYSQL</td>
			<td class="desc">Open-source relational database management system (RDBMS).</td>
		</tr>
		<tr>
			<td>JUnit</td>
			<td class="desc">Test and test cases for utilities.</td>
		</tr>
		<tr>
			<td>Guava</td>
			<td class="desc">Google caching and utilities.</td>
		</tr>
		<tr>
			<td>HTML5 & CSS3</td>
			<td class="desc">Do i need to say more?</td>
		</tr>
		<tr>
			<td>iDangero.us</td>
			<td class="desc">CSS and JS for image sliding.</td>
		</tr>
		<tr>
			<td>Quartz</td>
			<td class="desc">Job scheduling library that can be integrated into a wide variety of Java applications.</td>
		</tr>
		<tr>
			<td>Superfish</td>
			<td class="desc">Enhanced Suckerfish-style for drop-down menu.</td>
		</tr>
		<tr>
			<td>TableSaw</td>
			<td class="desc">Creating table to fit different type of devices.</td>
		</tr>
	</tbody>
</table>
<p>
<a href="#" class="link">Back To Top</a>
</p>
<hr>
<div class="title"><a id="T2">GIT HUB PROJECT</a></div>
<table class="tablesaw" data-mode="stack">
	<thead>
		<tr>
			<th>ProjectName</th>
			<th>Description</th>
			<th>Location</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>jjUtility</td>
			<td class="desc">Common Utilities</td>
			<td><a href="https://github.com/yoonghan/jjUtility">Clone Me</a></td>
		</tr>
		<tr>
			<td>selfservicembean</td>
			<td class="desc">Build in services integrating with JBOSS to allow adhoc monitoring and refresh.</td>
			<td><a href="https://github.com/yoonghan/selfservicembean">Clone Me</a></td>
		</tr>
		<tr>
			<td>selfserviceweb</td>
			<td class="desc">Web directory that contains all the service. To span as ear in future.</td>
			<td><a href="https://github.com/yoonghan/selfservicembean">Clone Me</a></td>
		</tr>
		<tr>
			<td>selfserviceparent</td>
			<td class="desc">A regular pom file to keep track of api.</td>
			<td><a href="https://github.com/yoonghan/selfserviceparent">Clone Me</a></td>
		</tr>
		<tr>
			<td>selfservicedb</td>
			<td class="desc">Using JDBI to query the database. Supports internal caching. No memcached used.</td>
			<td><a href="https://github.com/yoonghan/selfservicedb">Clone Me</a></td>
		</tr>
		<tr>
			<td>selfservice logging</td>
			<td class="desc">Logging utility instead of log4j and slf4j(but extend able). Support scheduling to send error notification via email.</td>
			<td><a href="https://github.com/yoonghan/selfservicelogging">Clone Me</a></td>
		</tr>
	</tbody>
</table>
<p>
<a href="#" class="link">Back To Top</a>
</p>
<br/>
<%@include file="/template/footer/revision.jsp" %>
</body>
</html>