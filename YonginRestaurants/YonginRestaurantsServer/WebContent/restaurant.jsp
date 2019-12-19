<%@page import="com.YonginRestaurantsServer.controller.HttpUtil"%>
<%@page import="com.YonginRestaurantsServer.vo.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 관리</title>
<style rel="stylesheet">
</style>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>

<!-- 조건 검색부 -->
<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="restaurantSearch.do" method="post">
				<div class="form-group">
						코드  <input type="text" maxlength="20"
							id="code" name="code"/>
					</div>
					<div class="form-group">
						음식점 이름  <input type="text" id="name"
								name="name" />
					</div>
					<div class="form-group">
						주소  <input type="text" maxlength="50"
							id="address" name="address"/>
					</div>
					<div class="form-group">
						전화번호 <input type="text" maxlength="20"
							id="number" name="number" />
					</div>
					<div class="form-group">
						홈페이지  <input type="text" maxlength="100"
							id="homepage" name="homepage" />
					</div>
					<div class="form-group">
						위도  <input type="text" maxlength="20"
							id="latitude" name="latitude" />
					</div>
					<div class="form-group">
						경도  <input type="text" maxlength="20"
							id="longitude" name="longitude"/>
					</div>
					<div class="form-group">
						<button type="submit">조회</button>
					</div>
				</form><form action="restaurant.do" method="post">
				<div class="form-group">
						<button type="submit">초기화</button>
					</div></form></div></div>
					
					<form action="restaurantinsert.jsp" method="post">
				<div class="form-group">
						<button type="submit">식당 추가하기</button>
					</div></form>

<!-- 테이블 -->
<% ArrayList<Restaurant> data = (ArrayList<Restaurant>) request.getAttribute("data"); 
if(data==null){ HttpUtil.forward(request, response, "restaurant.do");}
else if(data.size()==0){%><h2>데이터 값이 없습니다.</h2><%}
else{
%>
<table border="1px" width="1500px" cellpadding="10px" cellspacing="5px" style="padding:10px; margin:10px;">
<tr>
<th>코드</th> <th>음식점 이름</th> <th>주소</th> <th>전화번호</th> <th>홈페이지</th> <th>위도</th> <th>경도</th>
</tr>
<% 
for(Restaurant r : data){%>
	<tr><td><a href="restaurantinfo.do?code=<%=r.getCode()%>"><%=r.getCode()%></a></td><td><%=r.getName()%></td><td><%= r.getAddress()%></td><td><%=r.getNumber()%></td><td><%=r.getHomepage()%></td><td><%=r.getLatitude() %></td><td><%=r.getLongitude()%></td></tr>
<% }
}%>
</table>
</body>
</html>