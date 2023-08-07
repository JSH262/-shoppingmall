package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class CartVO extends BaseVO 
{
	String userId;
	Long productId;
	Integer amount;
	String sellerId;

	String thumbnail;
	String productName;
	int discountPrice;
	String companyName;
	int deliveryPrice;
	int price;
	int productAmount;
	
	String fmtAmount;
	String fmtPrice;
	String fmtDeliveryPrice;			
	String fmtDiscountPrice;
	

	public CartVO() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public CartVO(String userId, Long productId, Integer amount, String sellerId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.sellerId = sellerId;
	}
	
	public CartVO(String userId, Long productId, Integer amount, String sellerId, String thumbnail,
			String productName, int discountPrice, String companyName, int deliveryPrice) {
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
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
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}	
	

	
}
