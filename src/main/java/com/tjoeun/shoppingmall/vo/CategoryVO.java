package com.tjoeun.shoppingmall.vo;

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
	
	@Override
	public String toString() {
		return "CategoryVO [id=" + id + ", name=" + name + ", lev=" + lev + ", gup=" + gup + ", seq=" + seq + ", useYn="
				+ useYn + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", type=" + type + "]";
	}
}
