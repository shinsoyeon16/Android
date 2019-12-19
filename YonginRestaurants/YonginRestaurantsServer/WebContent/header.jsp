<%@page import="com.YonginRestaurantsServer.controller.HttpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
String id = (String) session.getAttribute("id");
if(id==null){
	request.setAttribute("error", "세션 만료! 다시 로그인해주세요.");
	HttpUtil.forward(request, response, "");
}

String error = (String) request.getAttribute("error");
if(error != null) {
	out.println("<script language='javascript'>");
	out.println("alert('" + error + "');");
	out.println("</script>");
} %>
<nav style="margin:20px; list-style:none;">
	<ul style="list-style:none;">
		<li style="float:left; border:0; padding:0 0 0 0; margin:0 0 0 0;"><a href="restaurant.do">식당 관리</a>&nbsp;&nbsp;</li>
		<li style="float:left; border:0; padding:0 0 0 0; margin:0 0 0 0;"><a href="member.do">회원 관리</a></li>
	</ul>
</nav>
<form class="login" action="logout.do" method="post">
<button type="submit">로그아웃</button>
</form>
</body>
</html>