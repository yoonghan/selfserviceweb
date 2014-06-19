<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <%@include file="/template/header/common.jsp"%>
<style>
design {
	position: fixed;
	top: 40%;
	left: 32%;
	margin-top: -50px;
	margin-left: -100px;
	width: 50%;
	font-size: 19pt;
	color: black;
	font-family: Arial, Helvetica, sans-serif;
}
highlight{
	font-weight: bold;
}
</style>
<c:set var="menu" value="${func:querySubMenuList()}"/>
<%@include file="/template/header/menu.jsp" %>
<design>
JOM Jaring is a joint-ventured project started by:
<p/>
<highlight>Han:</highlight> A Java Programmer.
<p/>
<highlight>Lee Wan:</highlight> An experience and dedicated programmer and designer.
<p/>
<p/>
<objectives>
<highlight>Project objectives:</highlight><br/> 
<objective>
First Phase: Had a site running. duh!!<br/>
</objective><objective>
Second Phase: Exploring technologies.<br/>
</objective><objective>
Third Phase: Purpose is to create an interactive, fast-response and easy to be navigated website.<br/>
</objective><objective>
Fourth Phase: Create interactive image viewing.<br/>
</objective><objective>
Fifth: Calendar for reservation, booking.
</objective>
</objectives>
</design>
</body>
</html>