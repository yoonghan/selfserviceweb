<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <%@include file="/template/header/common.jsp"%>
<style>
contact {
	position: fixed;
	top: 40%;
	left: 10%;
	margin-top: -50px;
	margin-left: -100px;
	width: 100%;
	font-size: 19pt;
	color: black;
	font-family: Arial, Helvetica, sans-serif;
}
</style>
<c:set var="menu" value="${func:querySubMenuList()}"/>
<%@include file="/template/header/menu.jsp" %>
<contact>
We are available for contact via <a href="mailto:mailyoonghan@gmail.com">mailyoonghan@gmail.com</a>
</contact>
</body>
</html>