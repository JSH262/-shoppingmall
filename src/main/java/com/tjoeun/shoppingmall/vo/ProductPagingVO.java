package com.tjoeun.shoppingmall.vo;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

public class ProductPagingVO extends BaseVO
{
	Integer currentPage;
	Integer totalPage;
	Integer totalCount;
	//Integer startNo;
	//Integer endNo;
	Integer startPage;
	Integer endPage;
	Integer pageSize;
	
	String searchCategory;
	String searchValue;
	
	
	public ProductPagingVO()
	{}
	
	public ProductPagingVO(Integer currentPage, Integer totalCount, Integer pageSize)
	{
		this.calPage(currentPage, totalCount, pageSize);
	}
	
		
	public ProductPagingVO(HttpServletRequest request) throws Exception {
		this.init(request);
	}

	public void calPage(Integer currentPage, Integer totalCount, Integer pageSize)
	{
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		
		this.totalPage = (this.totalCount - 1 ) / this.pageSize + 1;
		this.currentPage = Math.min(this.currentPage, this.totalPage);
		this.startNo = (this.currentPage - 1) * this.pageSize + 1; 		// DB용
		this.endNo = Math.min(this.totalCount, this.startNo + this.pageSize - 1); // DB용
		this.startPage = (this.currentPage - 1) / 10 * 10 + 1;
		this.endPage = Math.min(this.startPage + 9, this.totalPage);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/*
	public Integer getStartNo() {
		return startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}
	*/

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
	
	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
	
}
