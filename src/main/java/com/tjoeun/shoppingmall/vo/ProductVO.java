package com.tjoeun.shoppingmall.vo;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

public class ProductVO extends BaseVO
{
	//Integer rnum;
	Long id;
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
	
	String fmtAmount; 	//문자열 포멧이 적용된 상품의 수량(n개)
	String fmtPrice;	//문자열 포멧이 적용된 상품의 가격(0,000원)
	String fmtDiscount;	//문자열 포멧이 적용된 상품의 할인률(n%)
	String fmtDeliveryPrice;	//문자열 포멧이 적용된 배송료(0,000원)
	String fmtDiscountPrice;	//문자열 포멧이 적용된 상품의 할인률이 적용된 상품의 가격(0,000원)
	Integer discountPrice;		//상품의 할인율이 적용된 상품의 가격(00000)
	String companyName;			//상품의 업체명
	String categoryName;
	
	public ProductVO()
	{}
	

	/*
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	public String getFmtDiscount() {
		return fmtDiscount;
	}

	public void setFmtDiscount(String fmtDiscount) {
		this.fmtDiscount = fmtDiscount;
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

	public Integer getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
	
}
