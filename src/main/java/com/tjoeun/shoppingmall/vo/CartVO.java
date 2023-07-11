package com.tjoeun.shoppingmall.vo;

public class CartVO {
	String userId;
	Integer productId;
	Integer amount;
	Integer sellerId;
		
	String thumbnail;
	String productName;
	String discountPrice;
	String companyName;
	String deliveryPrice;
	
	public CartVO() {
	}
	
	public CartVO(String userId, Integer productId, Integer amount, Integer sellerId) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.amount = amount;
		this.sellerId = sellerId;
	}
	
	public CartVO(String userId, Integer productId, Integer amount, Integer sellerId, String thumbnail,
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
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
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
	@Override
	public String toString() {
		return "CartVO [userId=" + userId + ", productId=" + productId + ", amount=" + amount + ", sellerId=" + sellerId
				+ ", thumbnail=" + thumbnail + ", productName=" + productName + ", discountPrice=" + discountPrice
				+ ", companyName=" + companyName + ", deliveryPrice=" + deliveryPrice + "]";
	}
	
}
