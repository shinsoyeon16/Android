<%@page import="com.YonginRestaurantsServer.vo.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.YonginRestaurantsServer.service.Service"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "org.json.simple.*"%>
<%@ page import ="java.sql.*" %>
<%
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();
JSONObject jObject = new JSONObject();
Service service = Service.getInstanse();
ArrayList<Restaurant> list = service.selectRestaurant();
String result="";

int i=0;
for(Restaurant r : list){
	result += r.getName()+"&"+r.getAddress()+"&"+r.getNumber()+"&"+r.getHomepage()+"&"+r.getLatitude()+"&"+r.getLongitude()+"&"+r.getCode()+"#";
}
result = result.substring(0, result.length()-1);
jObject.put("result1", result);
jObject.put("result2", "");
jObject.put("result3", "");

jArray.add(0,jObject);
jsonMain.put("List",jArray);
out.println(jsonMain.toJSONString());
  System.out.println("마커 정보 요청 : "+LocalDateTime.now()+" , "+jsonMain.toJSONString());
%>