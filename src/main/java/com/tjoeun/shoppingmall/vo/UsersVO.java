package com.tjoeun.shoppingmall.vo;

public class UsersVO {
	String id;
	String password;
	String email;
	String phone;
	String type;
	String name;
	String useYn;
	String createDate;
	String modifyDate;
	String companyId;
	
	public UsersVO(String id, String password, String email, String phone, String type, String name, String companyId) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.type = type;
		this.name = name;
		this.companyId = companyId;
	}
	public UsersVO(String id, String password, String email, String phone, String type, String name) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.type = type;
		this.name = name;
	}
	public UsersVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public UsersVO()
	{}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Override
	public String toString() {
		return "UsersVO [id=" + id + ", password=" + password + ", email=" + email + ", phone=" + phone + ", type="
				+ type + ", name=" + name + ", useYn=" + useYn + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", companyId=" + companyId + "]";
	}
	
}