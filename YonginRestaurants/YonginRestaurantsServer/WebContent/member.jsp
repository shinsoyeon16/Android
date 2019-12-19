<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="com.YonginRestaurantsServer.controller.HttpUtil"%>
<%@page import="com.YonginRestaurantsServer.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
</head>
<body>
<header>
	<jsp:include page="header.jsp"></jsp:include>
</header>
<!-- 조건 검색부 -->
<div class="form">
			<div class="form-content" style="border:1px solid #ccc; padding:10px; margin:10px;">
				<form action="memberSearch.do" method="post">
					<div class="form-group">
						아이디  <input type="text" id="id"
								name="id"/>
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
						로그인 상태  <input type="radio" 
							id="login" name="login" value="login"/>로그인
							<input type="radio" maxlength="11"
							id="login" name="login" value="logout"/>로그아웃
					</div>
					<div class="form-group">
						마지막 접속 시간  <input type="radio"
							id="loginlog" name="loginlog" value="oneweek"/>7주일
							<input type="radio"
							id="loginlog" name="loginlog" value="onemonth"/>1개월
							<input type="radio"
							id="loginlog" name="loginlog" value="threemonth"/>3개월 
							<input type="radio"
							id="loginlog" name="loginlog" value="sixmonth"/>6개월
							<input type="radio"
							id="loginlog" name="loginlog" value="oneyear"/>1년 
					</div>
					<div class="form-group">
						<button type="submit">조회</button>
					</div>
				</form>
				<form action="member.do" method="post">
				<div class="form-group">
						<button type="submit">초기화</button>
					</div></form>
	</div>
</div>

<form action="memberinsert.jsp" method="post">
				<div class="form-group">
						<button type="submit">회원 추가</button>
					</div></form>
<!-- 테이블 -->
<% ArrayList<Member> data = (ArrayList<Member>) request.getAttribute("data"); 
if(data==null){ HttpUtil.forward(request, response, "member.do");}
else if(data.size()==0){%><h2>데이터 값이 없습니다.</h2><%}
else{
%>
<table border="1px" width="1500px" cellpadding="10px" cellspacing="5px" style="padding:10px; margin:10px;">
<tr>
<th>아이디</th> <th>이름</th> <th>핸드폰번호</th> <th>마지막 접속 기록</th> 
</tr>
<% 
for(Member m : data){%>
 	<tr><td><a href="memberinfo.do?id=<%=m.getId()%>"><%=m.getId()%></a></form></td><td><%= m.getName()%></td><td><%= m.getPhonenumber()%></td><td><%if(m.getLoginlog().toString().equals("2000-01-01T00:00")){
	%> - <% } else { %><%=m.getLoginlog().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))%> <%} %></td></tr>
<% }
}%>
</table>
</body>
</html>