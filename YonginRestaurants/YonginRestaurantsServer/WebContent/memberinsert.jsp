<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
		<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="memberInsert.do" method="post">
					<div class="form-group">
						아이디  <input type="text" id="id"
								name="id"/>
					</div>
					<div class="form-group">
						비밀번호  <input type="password" maxlength="20"
							id="password" name="password"/>
					</div>
					<div class="form-group">
						비밀번호 확인 <input type="password" maxlength="20"
							id="password2" name="password2"/>
					</div>
					<div class="form-group">
						이름  <input type="text" maxlength="20"
							id="name" name="name"/>
					</div>
					<div class="form-group">
						핸드폰번호  <input type="text" maxlength="11"
							id="phonenumber" name="phonenumber"/>
					</div>
					<div class="form-group">
						<button type="submit">등록</button>
					</div>
				</form></div></div>
</body>
</html>