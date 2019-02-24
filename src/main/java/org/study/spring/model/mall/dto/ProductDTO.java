package org.study.spring.model.mall.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private int productID;
	private String productName;
	private int price;
	private String description;
	private String imgURL;
	private MultipartFile mf;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public MultipartFile getMf() {
		return mf;
	}
	public void setMf(MultipartFile mf) {
		this.mf = mf;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [productID=" + productID + ", productName=" + productName + ", price=" + price
				+ ", description=" + description + ", imgURL=" + imgURL + ", mf=" + mf + "]";
	}
}
