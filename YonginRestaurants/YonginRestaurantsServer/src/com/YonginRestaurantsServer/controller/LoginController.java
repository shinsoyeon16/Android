package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.YonginRestaurantsServer.service.Service;

public class LoginController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		Service service = Service.getInstanse();
		String dbPassword = service.checkAdmin(id);
		if (id.isEmpty() || password.isEmpty()) {
			request.setAttribute("error", "모든 항목을 입력해주세요.");
			HttpUtil.forward(request, response, "");
			return;
		}
		if (dbPassword == null) {
			request.setAttribute("error", "존재하지 않는 아이디입니다.");
			HttpUtil.forward(request, response, "");
			return;
		} else {
			if (!dbPassword.equals(password)) {
				request.setAttribute("error", "비밀번호가 다릅니다.");
				HttpUtil.forward(request, response, "");
				return;
			} else if (dbPassword.equals(password)) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				HttpUtil.forward(request, response, "restaurant.do");
				return;
			}

		}
	}

}