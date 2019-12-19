package com.YonginRestaurantsServer.service;

import java.util.ArrayList;

import com.YonginRestaurantsServer.dao.MemberDao;
import com.YonginRestaurantsServer.dao.RestaurantDao;
import com.YonginRestaurantsServer.vo.Member;
import com.YonginRestaurantsServer.vo.Restaurant;

public class Service {
	private static Service service = new Service();
	private MemberDao mdao = MemberDao.getInstance();
	private RestaurantDao rdao = RestaurantDao.getInstance();

	private Service() {
	}

	public static Service getInstanse() {
		return service;
	}
	public String checkAdmin(String id) {
		return mdao.checkAdmin(id);
	}
	public ArrayList<Restaurant> selectRestaurant() {
		return rdao.selectRestaurant();
	}
	public ArrayList<Member> selectMember() {
		return mdao.selectMember();
	}
	public Member selectMember(String id) {
		return mdao.selectMember(id);
	}
	public void DeleteMember(String id) {
		mdao.DeleteMember(id);
	}
	public void UpdateMember(Member member) {
		mdao.UpdateMember(member);
	}
	public void InsertMember(Member member) {
		mdao.InsertMember(member);
	}
	public ArrayList<Member> SearchMember(String a,String b,String c,String d,String e) {
		return mdao.SearchMember(a,b,c,d,e);
	}
	public void InsertRestaurant(Restaurant r) {
		rdao.InsertRestaurant(r);
	}
	public Restaurant selectRestaurant(String code) {
		return rdao.selectRestaurant(code);
	}
	public void DeleteRestaurant(String code) {
		rdao.DeleteRestaurant(code);
	}
	public void UpdateRestaurant(Restaurant r) {
		rdao.UpdateRestaurant(r);
	}
	public ArrayList<Restaurant> SearchRestaurant(String a,String b,String c,String d,String e, String f, String g){
		return rdao.SearchRestaurant(a,b,c,d,e,f,g);
	}
	public void Login(String id) {
		mdao.Login(id);
	}
	public void Logout(String id) {
		mdao.Logout(id);
	}
	public boolean SelectFavorite(String id, String code) {
		return mdao.SelectFavorite(id, code);
	}
	public void InsertFavorite(String id, String code) {
		 mdao.InsertFavorite(id, code);
	}
	public void DeleteFavorite(String id, String code) {
		 mdao.DeleteFavorite(id, code);
	}
}
