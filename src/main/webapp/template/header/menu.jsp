 <script src="${SERVER_LOCATION}/js/hoverIntent.js"></script>
 <link rel="stylesheet" href="${SERVER_LOCATION}/css/superfish.css" media="screen">
 <script src="${SERVER_LOCATION}/js/superfish.js"></script>
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
		${menu}
</div>