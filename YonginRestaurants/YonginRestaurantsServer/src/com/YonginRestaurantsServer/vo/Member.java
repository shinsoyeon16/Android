package com.YonginRestaurantsServer.vo;

import java.time.LocalDateTime;

public class Member {
	private String id;
	private String password;
	private String name;
	private String phonenumber;
	private LocalDateTime loginlog;
	
	public Member() {
		super();
	}
	public Member(String id, String password, String name, String phonenumber, LocalDateTime loginlog) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phonenumber = phonenumber;
		this.loginlog = loginlog;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public LocalDateTime getLoginlog() {
		return loginlog;
	}
	public void setLoginlog(LocalDateTime loginlog) {
		this.loginlog = loginlog;
	}
}
