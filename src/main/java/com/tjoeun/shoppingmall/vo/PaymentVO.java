package com.tjoeun.shoppingmall.vo;

import javax.servlet.http.HttpServletRequest;

public class PaymentVO extends BaseVO
{
	Integer id;
	String userId;
	String paymentNumber;
	String status;
	Long productOrderId;
	Long paymentPrice;
	
	public PaymentVO()
	{}
	public PaymentVO(HttpServletRequest req) throws Exception
	{
		this.init(req);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getProductOrderId() {
		return productOrderId;
	}
	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}
	public Long getPaymentPrice() {
		return paymentPrice;
	}
	public void setPaymentPrice(Long paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	@Override
	public String toString() {
		return "PaymentVO [id=" + id + ", userId=" + userId + ", paymentNumber=" + paymentNumber + ", status=" + status
				+ ", productOrderId=" + productOrderId + ", paymentPrice=" + paymentPrice + "]";
	}	
	
	
	
	
	
	
}
