package org.study.spring.model.message.dto;

public class UserDTO {
	private String userID;
	private String userPw;
	private String userName;
	private int userPoint;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	@Override
	public String toString() {
		return "UserDTO [userID=" + userID + ", userPw=" + userPw + ", userName=" + userName + ", userPoint="
				+ userPoint + "]";
	}
}
