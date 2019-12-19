package com.YonginRestaurantsServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.YonginRestaurantsServer.vo.Member;

public class MemberDao {
	private static MemberDao dao = new MemberDao();

	private MemberDao() {
	}

	public static MemberDao getInstance() {
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
	
	public String checkAdmin(String id) {
		String password = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select password from admin where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				password = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("mdao.checkAdmin error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return password;
	}
	
	public ArrayList<Member> selectMember() {
		ArrayList<Member> members = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), LocalDateTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				members.add(member);
			}
		} catch (Exception e) {
			System.out.println("mdao.selectMember error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return members;
	}
	public Member selectMember(String id) {
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), LocalDateTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			}
		} catch (Exception e) {
			System.out.println("mdao.selectMember error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return member;
	}
	
	public void DeleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from member where id = ?");
			pstmt.setString(1, id);
			pstmt.execute();
			
		} catch (Exception e) {
			System.out.println("mdao.DeleteMember" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void UpdateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set password = ?, name=?, phonenumber=? where id=?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhonenumber());
			pstmt.setString(4, member.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mdao.UpdateMember" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public void InsertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into member (id, password, name, phonenumber) VALUES (?, ?, ?, ?);");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhonenumber());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mdao.InsertMember" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	
	public ArrayList<Member> SearchMember(String a,String b,String c,String d,String ee) {
		ArrayList<Member> members = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where id LIKE ? and name LIKE ? and phonenumber LIKE ? and login Like ? and loginlog >= ?");
			pstmt.setString(1, a);
			pstmt.setString(2, b);
			pstmt.setString(3, c);
			pstmt.setString(4, d);
			pstmt.setString(5, ee);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Member member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), LocalDateTime.parse(rs.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				members.add(member);
			}
		} catch (Exception e) {
			System.out.println("mdao.SearchMember error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return members;
	}
	public void Login(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set loginlog=?, login=? where id=?");
			pstmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			pstmt.setInt(2, 1);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mdao.Login" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	public void Logout(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set login=? where id=?");
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mdao.Logout" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	public boolean SelectFavorite(String id, String code) {
		boolean a= false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from favorite where id =? and code =?");
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a = true;
			}
		} catch (Exception e) {
			System.out.println("mdao.SelectFavorite error : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return a;
	}
	public void InsertFavorite(String id, String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into favorite (id, code) VALUES (?, ?);");
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("mdao.InsertFavorite" + e);
		} finally {
			close(conn, pstmt);
		}
	}
	public void DeleteFavorite(String id, String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("delete from favorite where id=? and code =?");
			pstmt.setString(1, id);
			pstmt.setString(2, code);
			pstmt.execute();
		} catch (Exception e) {
			System.out.println("mdao.DeleteFavorite" + e);
		} finally {
			close(conn, pstmt);
		}
	}
}
