package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class ProductBreakdownListController 
{
	private static final long serialVersionUID = 1L;

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductOrderService productOrderService;
	
	@RequestMapping(value="/product/breakdown/list", method=RequestMethod.GET)
	protected String productBreakdownList(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		String sellerId = AttributeName.getUserId(request);
		
		model.addAttribute("totalSellPrice", productService.totalProductSellPrice(sellerId));
		
		return "product/breakdown/list";
	}
	
	
	@RequestMapping(value="/product/breakdown/list", method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		ProductOrderService poService = productOrderService;
		
		try 
		{
			UsersVO user = AttributeName.getUserData(request);
			if(user != null && UsersType.SELLER.equals(user.getType()))
			{
				JSONObject result = new JSONObject();
				ProductOrderVO params = new Gson().fromJson(Util.toBody(request), ProductOrderVO.class);
				if(params == null)
					params = new ProductOrderVO();
				
				params.setSellerId(user.getId());
				params.setChoose("breakdown");
				
				Integer currentPage = params.getCurrentPage();
				Integer pageSize = params.getPageSize();
				Integer totalPage = poService.totalCount(params);
				
				currentPage = currentPage != null ? currentPage : 1;
				pageSize = pageSize != null ? pageSize : 15;
				
				ProductPagingVO page = new ProductPagingVO(currentPage, totalPage, pageSize);
				
				
				
				params.setStartNo(page.getStartNo());
				params.setEndNo(page.getEndNo());
				
				result.put("paging", page);
				result.put("list", poService.selectList(params));
				retval.put("result", result);
				retval.put("code", 0);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retval.put("code", -999);
			retval.put("msg", e.getMessage());
		}
		

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
		
	}

}
