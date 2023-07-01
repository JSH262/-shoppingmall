package com.tjoeun.shoppingmall.vo;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class ProductPagingVO extends BaseVO
{
	int currentPage;
	int totalPage;
	int totalCount;
	int startNo;
	int endNo;
	int startPage;
	int endPage;
	int pageSize;
	
	
	
	public ProductPagingVO()
	{}
	
	public ProductPagingVO(int currentPage, int totalCount, int pageSize)
	{
		this.calPage(currentPage, totalCount, pageSize);
	}
	
		
	public void calPage(int currentPage, int totalCount, int pageSize)
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
