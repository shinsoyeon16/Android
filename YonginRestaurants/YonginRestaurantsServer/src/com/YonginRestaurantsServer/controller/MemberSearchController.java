package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Member;

public class MemberSearchController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Service service = Service.getInstanse();
		String id = (String) request.getParameter("id");
		String name = (String) request.getParameter("name");
		String phonenumber = (String) request.getParameter("phonenumber");
		String login = (String) request.getParameter("login");
		String loginlog = (String) request.getParameter("loginlog");
		
		if(id==null) id="";
		if(name==null) name="";
		if(phonenumber==null) phonenumber="";
		if(login==null) login="";
		if(loginlog==null) loginlog="";
		
		if(login.equals("login")) login="%1%";
		else if(login.equals("logout")) login="%0%";
		else login="%%";
		
		if(loginlog.equals("oneweek")) loginlog = LocalDateTime.now().minusWeeks(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		else if(loginlog.equals("onemonth")) loginlog = LocalDateTime.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		else if(loginlog.equals("threemonth")) loginlog = LocalDateTime.now().minusMonths(3).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		else if(loginlog.equals("sixmonth"))loginlog = LocalDateTime.now().minusMonths(6).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		else if(loginlog.equals("oneyear")) loginlog = LocalDateTime.now().minusYears(1).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		else loginlog="19000000000000";
		
		ArrayList<Member> member = service.SearchMember("%"+id+"%","%"+name+"%","%"+phonenumber+"%",login,loginlog);
		request.setAttribute("data", member);
		HttpUtil.forward(request, response, "member.jsp");
	}

}
