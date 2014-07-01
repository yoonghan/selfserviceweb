<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="About our venture.">
<link rel="author"	href="https://plus.google.com/u/1/114852108498604797792" />
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>	
<title>Architecture &amp; Technologies</title>
<link rel="stylesheet" href="${SERVER_LOCATION}/css/tablestack/dialog.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/tablestack/tablesaw.stackonly.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/submenu.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/ssarchitecture.css">
<script src="${SERVER_LOCATION}/js/tablestack/dialog.js"></script>
<script src="${SERVER_LOCATION}/js/tablestack/tablesaw.stackonly.js"></script>
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
</head>
<body>
   <menu>
   </menu>
	<div id="submenu">
		<ul>
			<li class="menuTitle">Contents:</li>
			<li><a href="#T0">Architecture</a></li>
			<li><a href="#T1">Technology</a></li>
			<li><a href="#T2">Project Downloads</a></li>
		</ul>
	</div>

<img src="${SERVER_LOCATION}/img/archtbanner.jpg" style="width:100%"></img>
	
<!--input type="checkbox" id="art_img" value="none" checked-->
<div class="fullbar" id="T0">
<h1>Architecture</h1>
</div>
<br>
<section class="addMargin">
<img src="${SERVER_LOCATION}/img/design.jpg"/>
</section>
<label for="art_img" class="addMargin"><span><i>As of Release 1.0</i></span></label>
<br><br>
<div class="fullbar" id="T1">
 <h1>Technology</h1>
 <h3>3rd party products being used.</h3>
</div>
<br/>
<table class="tablesaw addMargin" data-mode="stack">
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
			<td>Quartz</td>
			<td class="desc">Job scheduling library that can be integrated into a wide variety of Java applications.</td>
		</tr>
		<tr>
			<td>TableSaw</td>
			<td class="desc">Creating table to fit different type of devices.</td>
		</tr>
		<tr>
			<td>Vert.x</td>
			<td class="desc">Lightweight webserver for image loading and static contents.</td>
		</tr>
		<tr>
			<td>Vegas</td>
			<td class="desc">A nice image display with timer.</td>
		</tr>
	</tbody>
</table>
<p>
<a href="#" class="link">Back To Top</a>
</p>
<div class="fullbar" id="T2">
 <h1>GITHub project</h1>
 <h3>-- Fork me --</h3>
</div>
<br/>
<table class="tablesaw addMargin" data-mode="stack">
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
   <div class="footnote">
	<br id="menu">
	&copy;2014 All rights reserved - JOM Jaring<br/>
	If you are interested, do not hesitate to contact us.
	</div>
</body>
</html>