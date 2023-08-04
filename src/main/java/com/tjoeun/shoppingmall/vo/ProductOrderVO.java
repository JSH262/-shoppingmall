package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class ProductOrderVO extends BaseVO
{
	String userId;
	Long id;
	Integer productId;
	Integer productAmount;
	Integer productPrice;
	Integer productDeliveryPrice;
	String productName;
	String productThumbnail;
	Integer status;
	String createDate;
	
	String fmtProductAmount;
	String fmtProductPrice;
	String fmtProductDeliveryPrice;
	String fmtTotalProductPrice;
	
	public ProductOrderVO() {
	}
	public ProductOrderVO(HttpServletRequest request) throws Exception 
	{
		this.init(request);
	}
	
	public String getProductThumbnail() {
		return productThumbnail;
	}
	public void setProductThumbnail(String productThumbnail) {
		this.productThumbnail = productThumbnail;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductDeliveryPrice() {
		return productDeliveryPrice;
	}
	public void setProductDeliveryPrice(Integer productDeliveryPrice) {
		this.productDeliveryPrice = productDeliveryPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getFmtProductAmount() {
		return fmtProductAmount;
	}
	public void setFmtProductAmount(String fmtProductAmount) {
		this.fmtProductAmount = fmtProductAmount;
	}
	public String getFmtProductPrice() {
		return fmtProductPrice;
	}
	public void setFmtProductPrice(String fmtProductPrice) {
		this.fmtProductPrice = fmtProductPrice;
	}
	public String getFmtProductDeliveryPrice() {
		return fmtProductDeliveryPrice;
	}
	public void setFmtProductDeliveryPrice(String fmtProductDeliveryPrice) {
		this.fmtProductDeliveryPrice = fmtProductDeliveryPrice;
	}
	public String getFmtTotalProductPrice() {
		return fmtTotalProductPrice;
	}
	public void setFmtTotalProductPrice(String fmtTotalProductPrice) {
		this.fmtTotalProductPrice = fmtTotalProductPrice;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
