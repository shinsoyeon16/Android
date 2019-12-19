package com.YonginRestaurantsServer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Restaurant;

public class RestaurantInfoController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		String code = (String) request.getParameter("code");
		Restaurant restaurant = service.selectRestaurant(code);
		request.setAttribute("data", restaurant);
		HttpUtil.forward(request, response, "restaurantinfo.jsp");
	}

}
