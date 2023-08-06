package com.tjoeun.shoppingmall.vo;

import com.google.gson.Gson;

public class DestinationAddressVO 
{
	Long id;
	String userId;
	String addr1;
	String addr2;
	String name;
	String phone;
	String addrName;
	String reqMsg;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddrName() {
		return addrName != null ? addrName : "";
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	public String getReqMsg() {
		return reqMsg != null ? reqMsg : "";
	}
	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	
}
