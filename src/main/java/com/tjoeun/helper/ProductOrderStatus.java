package com.tjoeun.helper;




/*
 
	1. 기본
 						┌ 반품, 교환
						│
	결재완료 => 상품준비 => 배송중 => 배송완료 => 거래완료
	│			│				│			│
	│			│				│			└ 
	│			│				└ 반품, 교환
	│			└ 결재취소
	└ 결재취소


	2. 반품
	반품신청 => 회수중 => 회수완료 => 상품확인중 => 반품완료 => 환불완료
	
	
	3. 교환
	교환신청 => 회수중 => 회수온료 => 상품확인중 => 상품준비 => 배송중 => 배송완료 => 거래완료
	
	
	


*/
public enum ProductOrderStatus 
{
	NML_PAYMENT_COMPLETE(1, "결제완료"),
	NML_PRODUCT_READY(2, "상품준비"),
	NML_DELIVERING(3, "배송중"),
	NML_DELIVERY_COMPLETE(4, "배송완료"),
	NML_DEAL_COMPLETE(5, "거래완료"),	
		

	RE_RETURNS(101, "상품반품"),
	RE_RECALL(102, "회수중"),
	RE_RETURNS_COMPLETE(103, "회수완료"),
	RE_PRODUCT_CHECK(104, "상품확인"),
	RE_PRODUCT_COMPLETE(105, "반품완료"),
	RE_REFUND_COMPLETE(106, "환불완료"),
	
	
	EX_EXCHANGE(201, "상품교환"),
	EX_RECALL(202, "회수중"),
	EX_RETURNS_COMPLETEE(203, "회수완료"),
	EX_PRODUCT_CHECK(204, "상품확인중"),
	EX_PRODUCT_READY(205, "상품준비"),
	EX_DELIVERING(206, "배송중"),
	EX_DELIVERY_COMPLETE(207, "배송완료"),
	EX_DEAL_COMPLETE(208, "거래완료"),
	
	
	CANCEL(-1, "결제취소"),
	NONE(0, "default");
	
	
	int code;
	String msg;
	
	ProductOrderStatus(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public boolean equals(int code)
	{
		return this.code == code;
	}
	
	
	/*
	//주문 취소
	public static final int CANCEL = 1;
	
	//주문한 상품 교환
	public static final int EXCHANGE = 2;
	
	//결제 완료
	public static final int PAYMENT_COMPLATE = 3;
	
	//주문한 상품 반품
	public static final int RETURNS = 4;
	
	//상품 배송완료
	public static final int DELIVERY_COMPLATE = 5;
	
	//거래완료
	public static final int DEAL_COMPLATE = 6;
	*/
	
}
