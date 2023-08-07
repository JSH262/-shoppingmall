package com.tjoeun.vo;

import java.sql.Timestamp;

public class CompanyVO {
    private String Name;
    private String Id;
    private String Address1;
    private String Address2;
    private String Stamp;
    private String UseYn;
    private Timestamp CreateDate;

    public CompanyVO() {
    }

    public CompanyVO(String Name, String Id, String Address1, String Address2, String Stamp, String UseYn, Timestamp CreateDate) {
        this.Name = Name;
        this.Id = Id;
        this.Address1 = Address1;
        this.Address2 = Address2;
        this.Stamp = Stamp;
        this.UseYn = UseYn;
        this.CreateDate = CreateDate;
    }
    
    public CompanyVO(String Name, String Id, String Address1, String Address2, String Stamp) {
    	this.Name = Name;
    	this.Id = Id;
    	this.Address1 = Address1;
    	this.Address2 = Address2;
    	this.Stamp = Stamp;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    public String getStamp() {
        return Stamp;
    }

    public void setStamp(String Stamp) {
        this.Stamp = Stamp;
    }

    public String getUseYn() {
        return UseYn;
    }

    public void setUseYn(String UseYn) {
        this.UseYn = UseYn;
    }

    public Timestamp getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Timestamp CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    public String toString() {
        return "CompanyVO{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Address1='" + Address1 + '\'' +
                ", Address2='" + Address2 + '\'' +
                ", Stamp='" + Stamp + '\'' +
                ", UseYn='" + UseYn + '\'' +
                ", CreateDate=" + CreateDate +
                '}';
    }
}


