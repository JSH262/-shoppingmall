package com.tjoeun.helper;

public class ProductOrderStatus {
	
	/**
	 * 주문 취소 
	 */
	public static final int CANCEL = 0;
	
	/**
	 * 주문한 상품 교환
	 */
	public static final int EXCHANGE = 1;
	
	/**
	 * 결제 완료
	 */
	public static final int COMPLATE = 2;
	
	/**
	 * 주문한 상품 반품
	 */
	public static final int RETURNS = 3;
	

	/**
	 * 상품 배송완료 및 결제완료
	 */
	public static final int END = 4;
	
}
