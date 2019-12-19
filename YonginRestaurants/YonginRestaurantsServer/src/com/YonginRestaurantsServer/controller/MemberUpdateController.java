package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Member;

public class MemberUpdateController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Service service = Service.getInstanse();
		String id = (String) request.getParameter("id");
		String password = (String) request.getParameter("password");
		String password2 = (String) request.getParameter("password2");
		String name = (String) request.getParameter("name");
		String phonenumber = (String) request.getParameter("phonenumber");
		
		if (id.isEmpty() || password.isEmpty() || password2.isEmpty() || name.isEmpty() || phonenumber.isEmpty()) {
			request.setAttribute("error", "모든 항목을 입력해주세요.");
		}else if(password.equals(password2)) {
			Member member = new Member(id, password, name, phonenumber, LocalDateTime.now());
			service.UpdateMember(member);
			request.setAttribute("error", "회원정보가 성공적으로 수정되었습니다.");
		}else {
			request.setAttribute("error", "비밀번호 확인을 정확히 입력해주세요.");
		}

		HttpUtil.forward(request, response, "memberinfo.do?id="+id);
	}

}