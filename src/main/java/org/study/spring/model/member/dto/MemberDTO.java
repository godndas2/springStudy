package org.study.spring.model.member.dto;

import java.util.Date;

public class MemberDTO {

	private String userid;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate="
				+ joinDate + "]";
	}
	
	
}
