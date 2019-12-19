<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.YonginRestaurantsServer.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<% Member data = (Member) request.getAttribute("data"); %>

		<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="memberUpdate.do" method="post">
					<div class="form-group">
						아이디  <input type="text" id="id"  readonly="readonly"
								name="id" value="<%=data.getId() %>"/>
					</div>
					<div class="form-group">
						비밀번호  <input type="password" maxlength="20"
							id="password" name="password" value=""/>
					</div>
					<div class="form-group">
						비밀번호 확인 <input type="password" maxlength="20"
							id="password2" name="password2" value=""/>
					</div>
					<div class="form-group">
						이름  <input type="text" maxlength="20"
							id="name" name="name" value="<%=data.getName() %>"/>
					</div>
					<div class="form-group">
						핸드폰번호  <input type="text" maxlength="11"
							id="phonenumber" name="phonenumber" value="<%=data.getPhonenumber() %>"/>
					</div>
				<div class="form-group">
						마지막 접속 시간  <%if(data.getLoginlog().toString().equals("2000-01-01T00:00")){ %>
						<input type="text" readonly="readonly" value=" - " /> <% } else { %>
						<input type="text" readonly="readonly"
							 value="<%=data.getLoginlog().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%>"/> <%} %>
					</div>
					<div class="form-group">
						<button type="submit">수정</button>
					</div>
				</form>
				<form action="memberDelete.do?id=<%=data.getId() %>" method="post">
				<div class="form-group">
						<button type="submit">삭제</button>
					</div></form></div></div>
</body>
</html>