package com.YonginRestaurantsServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.YonginRestaurantsServer.vo.Restaurant;

public class RestaurantDao {
	private static RestaurantDao dao = new RestaurantDao();

	private RestaurantDao() {
	}

	public static RestaurantDao getInstance() {
		return dao;
	}
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/yonginrestaurants";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("connect" + e);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("Error:close1" + e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Error:close2" + e);
			}
		}
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("Error:close1" + e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Error:close2" + e);
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("Error:close3" + e);
			}
		}
	}
		
	public ArrayList<Restaurant> selectRestaurant() {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from restaurant");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				restaurants.add(restaurant);
			}
		} catch (Exception e) {
			System.out.println("rdao.selectRestaurant error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return restaurants;
	}
	
	public void InsertRestaurant(Restaurant r) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into restaurant (name, address, number, homepage, latitude, longitude) VALUES (?, ?, ?, ?,?,?);");
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getAddress());
			pstmt.setString(3, r.getNumber());
			pstmt.setString(4, r.getHomepage());
			pstmt.setString(5, r.getLatitude());
			pstmt.setString(6, r.getLongitude());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("rdao.InsertRestaurant" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public Restaurant selectRestaurant(String code) {
		Restaurant restaurant = new Restaurant();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from restaurant where code = ?");
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				restaurant = new Restaurant(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			}
		} catch (Exception e) {
			System.out.println("rdao.selectRestaurant error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return restaurant;
	}
	
	public void DeleteRestaurant(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from restaurant where code = ?");
			pstmt.setString(1, code);
			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("rdao.DeleteRestaurant" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void UpdateRestaurant(Restaurant r) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update restaurant set name=?, address=?, number=?, homepage=?, latitude=?, longitude=? where code=?");
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getAddress());
			pstmt.setString(3, r.getNumber());
			pstmt.setString(4, r.getHomepage());
			pstmt.setString(5, r.getLatitude());
			pstmt.setString(6, r.getLongitude());
			pstmt.setString(7, r.getCode());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("rdao.UpdateRestaurant" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public ArrayList<Restaurant> SearchRestaurant(String a,String b,String c,String d,String ee, String f, String g) {
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from restaurant where name LIKE ? and address LIKE ? and number LIKE ? and homepage LIKE ? and latitude LIKE ? and longitude LIKE ? and code LIKE ?");
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			pstmt.setString(3, c);
			pstmt.setString(4, d);
			pstmt.setString(5, ee);
			pstmt.setString(6, f);
			pstmt.setString(7, g);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant(rs.getString(1),rs.getString(2),rs.getString(3),
						rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				restaurants.add(restaurant);
			}
		} catch (Exception e) {
			System.out.println("rdao.SearchRestaurant error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return restaurants;
	}
}
