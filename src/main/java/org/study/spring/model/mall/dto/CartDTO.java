package org.study.spring.model.mall.dto;

public class CartDTO {
	
	private int cartID;
	private String userid;
	private String name;
	private int productID;
	private String productName;
	private int price;
	private int money;
	private int cnt;
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "CartDTO [cartID=" + cartID + ", userid=" + userid + ", name=" + name + ", productID=" + productID
				+ ", productName=" + productName + ", price=" + price + ", money=" + money + ", cnt=" + cnt + "]";
	}
	
	
}
