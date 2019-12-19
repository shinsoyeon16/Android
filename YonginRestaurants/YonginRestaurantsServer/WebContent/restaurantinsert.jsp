<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당 추가</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="restaurantInsert.do" method="post">
					<div class="form-group">
						음식점 이름  <input type="text" id="name"
								name="name"/>
					</div>
					<div class="form-group">
						주소  <input type="text" maxlength="50"
							id="address" name="address"/>
					</div>
					<div class="form-group">
						전화번호 <input type="text" maxlength="20"
							id="number" name="number"/>
					</div>
					<div class="form-group">
						홈페이지  <input type="text" maxlength="100"
							id="homepage" name="homepage"/>
					</div>
					<div class="form-group">
						위도  <input type="text" maxlength="20"
							id="latitude" name="latitude"/>
					</div>
					<div class="form-group">
						경도  <input type="text" maxlength="20"
							id="longitude" name="longitude"/>
					</div>
					<div class="form-group">
						<button type="submit">등록</button>
					</div>
				</form></div></div>
</body>
</html>