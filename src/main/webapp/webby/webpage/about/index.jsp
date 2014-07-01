<html lang="en">
<head>
<title>About Us</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="About our venture.">
<link rel="author"	href="https://plus.google.com/u/1/114852108498604797792" />
<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>	
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/submenu.css">
<link rel="stylesheet" href="${SERVER_LOCATION}/css/webby/ssabout.css">
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
	<div class="about" style="padding:10px 80px 20px 80px; margin:30px 40px;">
		<h1>About JOM Jaring</h1>
	    <intro>
		<strong>JOM Jaring website was an idea created by 2 masterminds. A boy and a girl. Together we are trying out
		new technologies to be implemented. While improving this, we both are trying to promote images to get it selling. The ultimate goal
		is to have a user friendly program.</strong>
		</intro>
	</div>
	
	<div id="container">
		<div id="row">
			<div id="desc">
				<img class="profile" src="${SERVER_LOCATION}/img/han.jpg" id="left"/>
				<h2>Yoong Han</h2>
				<intro>Came with an idea to start JOM Jaring after reading an inspiring book; titled, "The Monk Who Sold His Ferrari". He has decided
				to take charge of his life rather than relying only on his job to make a living. He had spend most of his lifeline in Java. <br><br>
				He's in charge of the architecture and technology of this website.<br><br>
				Han's hobby varies from photography to outdoor hiking; which he believes out there, are many who shared the same interest as well.</intro>
			</div>
		</div>
		<div id="row">
			<div id="desc">
				<img class="profile" src="${SERVER_LOCATION}/img/leewan.jpg" id="right"/>
				<h2>Gladys Lee Wan</h2>
				<intro>She's the heart and soul of the project. She's a very young and talented web programmer. It's hard to find a lady programmer, any day, any time.
				She's an experienced front-end designer and coder.<br><br>
				She is the approver of the web page design and layouts.<br><br>
				"Beauty is to an eye of the believer." Gladys is the inspiration to Han and that's how it all begin.</intro>
			</div>
		</div>
	</div>
	
	<div class="fullbar">
	<h1> Interested About Us? </h1>
	</div>
	<div id="contactus" style="margin:20px;">
		<h1>CONTACT US !</h1>
		<description>If you are interested to contact us, either for the development, progress, advice, photos, freelance, etc. You may get to us via:</description>
		<br/><br/>
		<contact>Email: <a href="mailto:jomjaring@gmail.com">jomjaring@gmail.com</a></contact> or <contact>
		<a href="mailto:mailyoonghan@gmail.com">mailyoonghan@gmail.com</a></contact>
		<!--contact>Facebook:</contact-->
	</div>
	<br><br/>
	<div class="footnote">
	<br id="menu">
	&copy;2014 All rights reserved - JOM Jaring<br/>
	If you are interested, do not hesitate to contact us.
	</div>
</body>
</html>