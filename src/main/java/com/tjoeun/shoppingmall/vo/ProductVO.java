package com.tjoeun.shoppingmall.vo;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

public class ProductVO extends BaseVO
{
	Integer rnum;
	Integer id;
	Integer categoryId;
	String name;
	Integer amount;
	Integer price;
	Integer discount;
	Integer deliveryPrice;
	String contents;
	String thumbnail;
	String sellerId;
	String useYn;
	String createDate;
	String modifyDate;
	
	public ProductVO()
	{}
	
	public ProductVO(MultipartRequest request) throws Exception
	{
		 this.init(request);
	}
	
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(Integer deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	@Override
	public String toString()
	{
		return 	new Gson().toJson(this);
	}
	
	public String contents() {
		return "ProductVO [rnum=" + rnum + ", id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", amount="
				+ amount + ", price=" + price + ", discount=" + discount + ", deliveryPrice=" + deliveryPrice
				+ ", contents=" + contents + ", thumbnail=" + thumbnail + ", sellerId=" + sellerId + ", useYn=" + useYn
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
