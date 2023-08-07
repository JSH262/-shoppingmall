package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class ProductOrderVO extends BaseVO
{
	String userId;
	Long id;
	Long productId;
	Integer productAmount;
	Integer productPrice;
	Integer productDeliveryPrice;
	String productName;
	String productThumbnail;
	Integer status;
	String createDate;
	String modifyDate;
	String sellerId;
	
	String fmtProductAmount;
	String fmtProductPrice;
	String fmtProductDeliveryPrice;
	String fmtTotalProductPrice;
	
	
	String deliveryName;
	String deliveryAddr1;
	String deliveryAddr2;
	String deliveryPhone;
	String deliveryRequestMsg;
	
	
	public ProductOrderVO() {
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
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
	
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryAddr1() {
		return deliveryAddr1;
	}
	public void setDeliveryAddr1(String deliveryAddr1) {
		this.deliveryAddr1 = deliveryAddr1;
	}
	public String getDeliveryAddr2() {
		return deliveryAddr2;
	}
	public void setDeliveryAddr2(String deliveryAddr2) {
		this.deliveryAddr2 = deliveryAddr2;
	}
	public String getDeliveryPhone() {
		return deliveryPhone;
	}
	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}
	public String getDeliveryRequestMsg() {
		return deliveryRequestMsg;
	}
	public void setDeliveryRequestMsg(String deliveryRequestMsg) {
		this.deliveryRequestMsg = deliveryRequestMsg;
	}	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
