package com.YonginRestaurantsServer.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class FrontController extends HttpServlet{
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		list = new HashMap<String, Controller>();
		list.put("/login.do", new LoginController());
		list.put("/logout.do", new LogoutController());
		list.put("/restaurant.do", new RestaurantViewController());
		list.put("/member.do", new MemberViewController());
		list.put("/memberinfo.do", new MemberInfoController());
		list.put("/memberUpdate.do", new MemberUpdateController());
		list.put("/memberDelete.do", new MemberDeleteController());
		list.put("/memberInsert.do", new MemberInsertController());
		list.put("/memberSearch.do", new MemberSearchController());
		list.put("/restaurant.do", new RestaurantViewController());
		list.put("/restaurantinfo.do", new RestaurantInfoController());
		list.put("/restaurantUpdate.do", new RestaurantUpdateController());
		list.put("/restaurantDelete.do", new RestaurantDeleteController());
		list.put("/restaurantInsert.do", new RestaurantInsertController());
		list.put("/restaurantSearch.do", new RestaurantSearchController());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());
		Controller subController = list.get(path);
		subController.execute(request, response);
	}
}
