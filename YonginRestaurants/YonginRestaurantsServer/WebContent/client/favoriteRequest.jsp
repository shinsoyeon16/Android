<%@page import="com.YonginRestaurantsServer.service.Service"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "org.json.simple.*"%>
<%@ page import ="java.sql.*" %>
<%
String id = request.getParameter("id");
String code = request.getParameter("code");
String data = request.getParameter("data");
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();
JSONObject jObject = new JSONObject();
Service service = Service.getInstanse();

if(data.equals("1")){
	if(service.SelectFavorite(id, code)) service.DeleteFavorite(id,code);
	else service.InsertFavorite(id, code);
	
}
	if(service.SelectFavorite(id, code)){
		jObject.put("result1", "true");
	    jObject.put("result2", " ");
	    jObject.put("result3", " ");
	}
	else{
		jObject.put("result1", "false");
	    jObject.put("result2", " ");
	    jObject.put("result3", " ");
	}
	
  jArray.add(0,jObject);
  jsonMain.put("List",jArray);
  out.println(jsonMain.toJSONString());
  System.out.println("즐겨찾기 요청 : "+LocalDateTime.now()+" , "+jsonMain.toJSONString());
%>