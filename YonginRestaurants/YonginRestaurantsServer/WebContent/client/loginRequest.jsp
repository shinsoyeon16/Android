<%@page import="com.YonginRestaurantsServer.service.Service"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "org.json.simple.*"%>
<%@ page import ="java.sql.*" %>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();
JSONObject jObject = new JSONObject();
int i=0;
Service service = Service.getInstanse();
if(service.selectMember(id).getPassword()!=null){
	if(pw.equals(service.selectMember(id).getPassword())){
		service.Login(id);
		jObject.put("result1", "succed");
	    jObject.put("result2", " ");
	    jObject.put("result3", " ");
	    i=1;
	}
}

if(i==0){
	 jObject.put("result1", "failed");
	 jObject.put("result2", " ");
	 jObject.put("result3", " ");
}
  jArray.add(0,jObject);
  jsonMain.put("List",jArray);
  out.println(jsonMain.toJSONString());
  System.out.println("로그인 요청 : "+LocalDateTime.now()+" , "+jsonMain.toJSONString());
%>