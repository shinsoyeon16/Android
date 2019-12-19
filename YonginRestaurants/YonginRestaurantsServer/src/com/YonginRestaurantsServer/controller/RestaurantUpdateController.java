package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Restaurant;

public class RestaurantUpdateController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Service service = Service.getInstanse();
		String name = (String) request.getParameter("name");
		String address = (String) request.getParameter("address");
		String number = (String) request.getParameter("number");
		String homepage = (String) request.getParameter("homepage");
		String latitude = (String) request.getParameter("latitude");
		String longitude = (String) request.getParameter("longitude");
		String code = (String) request.getParameter("code");
		if(homepage==null) homepage="";
		if (name.isEmpty() || address.isEmpty() || number.isEmpty() || longitude.isEmpty() || latitude.isEmpty()) {
			request.setAttribute("error", "모든 항목을 입력해주세요.");
		}else {
			Restaurant restaurant = new Restaurant(name, address, number, homepage, latitude, longitude, code);
			service.UpdateRestaurant(restaurant);
			request.setAttribute("error", "식당정보가 성공적으로 수정되었습니다.");
			}
		HttpUtil.forward(request, response, "restaurantinfo.do?code="+code);
	
	}

}
