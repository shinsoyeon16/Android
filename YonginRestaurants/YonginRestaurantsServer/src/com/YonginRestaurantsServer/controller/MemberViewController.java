package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Member;

public class MemberViewController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		ArrayList<Member> member = service.selectMember();
		request.setAttribute("data", member);
		HttpUtil.forward(request, response, "member.jsp");
	}
}
