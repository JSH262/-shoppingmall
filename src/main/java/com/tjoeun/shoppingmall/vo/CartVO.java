package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

public class CartVO extends BaseVO 
{
	String userId;
	Integer productId;
	Integer amount;
	String sellerId;
	
	String thumbnail;
	String productName;
	String discountPrice;
	String companyName;
	String deliveryPrice;
	
	
	public CartVO() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public CartVO(HttpServletRequest request) throws Exception 
	{
		this.init(request);
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(String deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}	
	
	
	
	
	
	
}
