<%@page import="com.YonginRestaurantsServer.vo.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 정보</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<% Restaurant data = (Restaurant) request.getAttribute("data"); %>
<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="restaurantUpdate.do" method="post">
				<div class="form-group">
						코드  <input type="text" maxlength="20" readonly="readonly"
							id="code" name="code" value="<%=data.getCode() %>"/>
					</div>
					<div class="form-group">
						음식점 이름  <input type="text" id="name"
								name="name" value="<%=data.getName() %>"/>
					</div>
					<div class="form-group">
						주소  <input type="text" maxlength="50"
							id="address" name="address" value="<%=data.getAddress() %>"/>
					</div>
					<div class="form-group">
						전화번호 <input type="text" maxlength="20"
							id="number" name="number" value="<%=data.getNumber() %>"/>
					</div>
					<div class="form-group">
						홈페이지  <input type="text" maxlength="100"
							id="homepage" name="homepage" value="<%=data.getHomepage() %>"/>
					</div>
					<div class="form-group">
						위도  <input type="text" maxlength="20"
							id="latitude" name="latitude" value="<%=data.getLatitude() %>"/>
					</div>
					<div class="form-group">
						경도  <input type="text" maxlength="20"
							id="longitude" name="longitude" value="<%=data.getLongitude() %>"/>
					</div>
					<div class="form-group">
						<button type="submit">수정</button>
					</div>
				</form><form action="restaurantDelete.do?code=<%=data.getCode() %>" method="post">
				<div class="form-group">
						<button type="submit">삭제</button>
					</div></form></div></div>
</body>
</html>