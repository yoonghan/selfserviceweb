<c:set var="subMainMenu" value="${func:querySubMenuList()}"/>
 <script src="../js/hoverIntent.js"></script>
 <script src="../js/jquery.js"></script>
 <link rel="stylesheet" href="../css/superfish.css" media="screen">
 <script src="../js/superfish.js"></script>
 <style>
.headSeperator {
 margin-top:50px;
}
.sf-menu{
 z-index: 100;
 position: absolute;
}
 </style>
 <script>
(function($){ 
 $(document).ready(function(){
	var menu = $('#menu').superfish({
 	});
	 $('.destroy').on('click', function(){
		menu.superfish('destroy');
	 });
	 $('.init').on('click', function(){
		menu.superfish();
	 });
	 $('.open').on('click', function(){
		menu.children('li:first').superfish('show');
	 });
	 $('.close').on('click', function(){
		menu.children('li:first').superfish('hide');
	 });
 });
})(jQuery);
 </script>
</head>
<body>
<div class="headSeperator">
	<ul class="sf-menu" id="menu">
		${subMainMenu}
	</ul>
</div>