package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Restaurant;

public class RestaurantViewController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = Service.getInstanse();
		ArrayList<Restaurant> restaurant = service.selectRestaurant();
		request.setAttribute("data", restaurant);
		HttpUtil.forward(request, response, "restaurant.jsp");
	}
}
