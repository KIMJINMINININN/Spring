package com.spring.member.vo;

import java.util.Date;

public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String joinDate;
	
	public MemberVO(){
		System.out.println("MemberVO 생성자 호출");
	}
	
	public MemberVO(String id, String pwd, String name, String eamil){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}
	
	public MemberVO(String id, String pwd, String name, String eamil, String joinDate){
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.joinDate = joinDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String string) {
		this.joinDate = string;
	}

	
}
