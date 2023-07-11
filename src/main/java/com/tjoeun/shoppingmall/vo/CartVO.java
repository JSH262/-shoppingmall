package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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
	
	String fmtAmount;
	String fmtPrice;
	String fmtDeliveryPrice;			
	String fmtDiscountPrice;
	
	

	public CartVO() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public CartVO(HttpServletRequest request) throws Exception 
	{
		this.init(request);
	}
	
	public CartVO(String userId, Integer productId, Integer amount, String sellerId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.sellerId = sellerId;
	}
	
	public CartVO(String userId, Integer productId, Integer amount, String sellerId, String thumbnail,
			String productName, String discountPrice, String companyName, String deliveryPrice) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.sellerId = sellerId;
		this.thumbnail = thumbnail;
		this.productName = productName;
		this.discountPrice = discountPrice;
		this.companyName = companyName;
		this.deliveryPrice = deliveryPrice;
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
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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


	public String getFmtAmount() {
		return fmtAmount;
	}

	public void setFmtAmount(String fmtAmount) {
		this.fmtAmount = fmtAmount;
	}

	public String getFmtPrice() {
		return fmtPrice;
	}

	public void setFmtPrice(String fmtPrice) {
		this.fmtPrice = fmtPrice;
	}

	public String getFmtDeliveryPrice() {
		return fmtDeliveryPrice;
	}

	public void setFmtDeliveryPrice(String fmtDeliveryPrice) {
		this.fmtDeliveryPrice = fmtDeliveryPrice;
	}

	public String getFmtDiscountPrice() {
		return fmtDiscountPrice;
	}

	public void setFmtDiscountPrice(String fmtDiscountPrice) {
		this.fmtDiscountPrice = fmtDiscountPrice;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}	
	

	
}
