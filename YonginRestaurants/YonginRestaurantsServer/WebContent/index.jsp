<%@page import="com.YonginRestaurantsServer.controller.HttpUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>
<body>
<%
String id = (String) session.getAttribute("id");
if(id!=null){
	HttpUtil.forward(request, response, "restaurant.jsp");
}

String error = (String) request.getAttribute("error");
if(error != null) {
	out.println("<script language='javascript'>");
	out.println("alert('" + error + "');");
	out.println("</script>");
}	%>


		<div class="form">
			<div class="form-content">
				<form action="login.do" method="post">
					<div class="form-group">
						아이디  <input type="text" id="id"
								name="id"/>
					</div>
					<div class="form-group">
						비밀번호  <input type="password"
							id="password" name="password">
					</div>
				
					<div class="form-group">
						<button type="submit">로그인</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>