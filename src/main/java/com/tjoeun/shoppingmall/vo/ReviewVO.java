package com.tjoeun.shoppingmall.vo;

import java.sql.Timestamp;

public class ReviewVO {
    int id;
    String userId;
    String contents;
    int productId;
    int orderId;
    float score;
    char useYn;
    Timestamp createDate;
    
    public ReviewVO() {	}
    
    public ReviewVO(String userId) {
    	this.userId = userId;
    }

    public ReviewVO(int id, String userId, String contents, int productId, int orderId, float score, char useYn,
			Timestamp createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.contents = contents;
		this.productId = productId;
		this.orderId = orderId;
		this.score = score;
		this.useYn = useYn;
		this.createDate = createDate;
	}
    
    public ReviewVO(String userId, String contents, int productId, int orderId, float score) {
    	super();
    	this.userId = userId;
    	this.contents = contents;
    	this.productId = productId;
    	this.orderId = orderId;
    	this.score = score;
    }


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getOrderId() {
    	return orderId;
    }
    
    public void setOrderId(int orderId) {
    	this.orderId = orderId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public char getUseYn() {
        return useYn;
    }

    public void setUseYn(char useYn) {
        this.useYn = useYn;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "ReviewVO [id=" + id + ", userId=" + userId + ", contents=" + contents + ", productId=" + productId
				+ ", orderId=" + orderId + ", score=" + score + ", useYn=" + useYn + ", createDate=" + createDate + "]";
	}

    
}
