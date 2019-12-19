<%@page import="com.YonginRestaurantsServer.vo.Member"%>
<%@page import="com.YonginRestaurantsServer.service.Service"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "org.json.simple.*"%>
<%@ page import ="java.sql.*" %>
<%
String id = request.getParameter("id");
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();
JSONObject jObject = new JSONObject();
Service service = Service.getInstanse();
Member m = service.selectMember(id);

jObject.put("result1", m.getName());
jObject.put("result2", m.getPhonenumber());
jObject.put("result3", "");

jArray.add(0,jObject);
jsonMain.put("List",jArray);
out.println(jsonMain.toJSONString());
  System.out.println("회원 정보 요청 : "+LocalDateTime.now()+" , "+jsonMain.toJSONString());
%>