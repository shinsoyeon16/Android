package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.YonginRestaurantsServer.service.Service;
import com.YonginRestaurantsServer.vo.Restaurant;

public class RestaurantSearchController implements Controller{
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
		if( name ==null) name="";
		if( address ==null) address="";
		if( number ==null) number="";
		if(homepage  ==null)homepage ="";
		if( latitude ==null)latitude ="";
		if(longitude  ==null)longitude ="";
		if(  code==null)code ="";
		ArrayList<Restaurant> r = service.SearchRestaurant("%"+name +"%","%"+address+"%","%"+number+"%","%"+homepage+"%","%"+latitude+"%","%"+longitude+"%","%"+code+"%");
		request.setAttribute("data", r);
		HttpUtil.forward(request, response, "restaurant.jsp");
	}

}
