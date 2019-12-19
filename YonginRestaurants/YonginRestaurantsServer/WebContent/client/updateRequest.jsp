<%@page import="com.YonginRestaurantsServer.vo.Member"%>
<%@page import="com.YonginRestaurantsServer.service.Service"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "org.json.simple.*"%>
<%@ page import ="java.sql.*" %>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String phonenumber = request.getParameter("phonenumber");
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();
JSONObject jObject = new JSONObject();

Service service = Service.getInstanse();
service.UpdateMember(new Member(id, pw, name, phonenumber, LocalDateTime.now()));
	jObject.put("result1", "succed");
    jObject.put("result2", "");
    jObject.put("result3", "");
    
  jArray.add(0,jObject);
  jsonMain.put("List",jArray);
  out.println(jsonMain.toJSONString());
  System.out.println("회원수정 요청 : "+LocalDateTime.now()+" , "+jsonMain.toJSONString());
%>