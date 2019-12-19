package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Member;

public class MemberDeleteController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		String id = (String) request.getParameter("id");
		service.DeleteMember(id);
		request.setAttribute("error", "회원정보가 성공적으로 삭제되었습니다.");
		HttpUtil.forward(request, response, "member.do");
	}

}
