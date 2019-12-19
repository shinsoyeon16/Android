package com.YonginRestaurantsServer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;

public class RestaurantDeleteController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		String code = (String) request.getParameter("code");
		service.DeleteRestaurant(code);
		request.setAttribute("error", "식당정보가 성공적으로 삭제되었습니다.");
		HttpUtil.forward(request, response, "restaurant.do");
	}

}
