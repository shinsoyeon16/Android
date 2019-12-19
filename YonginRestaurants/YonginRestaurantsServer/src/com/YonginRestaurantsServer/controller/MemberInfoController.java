package com.YonginRestaurantsServer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Member;

public class MemberInfoController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		String id = (String) request.getParameter("id");
		Member member = service.selectMember(id);
		request.setAttribute("data", member);
		HttpUtil.forward(request, response, "memberinfo.jsp");
	}
}
