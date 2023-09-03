package com.tjoeun.shoppingmall.vo;

import com.google.gson.Gson;

public class CategoryVO 
{
	Integer id;
	String name;
	Integer lev;
	Integer gup;
	Integer seq;
	String useYn;
	String createDate;
	String modifyDate;
	String type;
	Integer pid;
	
	
	
	
	
	
	
	
	public CategoryVO() {
		
	}
	
	public CategoryVO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}





	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLev() {
		return lev;
	}
	public void setLev(Integer lev) {
		this.lev = lev;
	}
	public Integer getGup() {
		return gup;
	}
	public void setGup(Integer gup) {
		this.gup = gup;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
