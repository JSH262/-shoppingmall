package com.tjoeun.shoppingmall.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;

@Controller
public class ProductReviewListController 
{
	static final Logger log = LoggerFactory.getLogger(ProductReviewListController.class);
	
	private static final long serialVersionUID = 1L;

	@Autowired
	ProductService productService;
	
	@ResponseBody
	@RequestMapping(value="/product/review/list", method=RequestMethod.POST)
	protected HashMap<String, Object> doGet(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ProductVO params)
	{
		HashMap<String, Object> retval = new HashMap<String, Object>();
		Integer currentPage = Util.toInt(params.getCurrentPage(), 1);
		Integer pageSize = Util.toInt(params.getPageSize(), 3);
		Integer totalCount = productService.selectProductReviewCount(params);
		ProductPagingVO paging = new ProductPagingVO(currentPage, totalCount, pageSize);
				
		params.setStartNo(paging.getStartNo());
		params.setEndNo(paging.getEndNo());
		retval.put("code", 0);
		retval.put("list", productService.selectProductReview(params));
		retval.put("paging", paging);
		
		return retval;
	}
}
