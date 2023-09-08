package com.tjoeun.shoppingmall.config.interceptor;

public class LoginChkData {
	private String redirectUrl;
	private boolean isCheck;
	
	
	
	public LoginChkData(String redirectUrl, boolean isCheck) {
		super();
		this.redirectUrl = redirectUrl;
		this.isCheck = isCheck;
	}
	
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public boolean isCheck() {
		return isCheck;
	}
	
	
	
	
}
