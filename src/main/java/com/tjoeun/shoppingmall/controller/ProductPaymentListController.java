package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;


//@WebServlet("/product/payment/list")
@Controller
public class ProductPaymentListController {
	private static final long serialVersionUID = 1L;

	
	@RequestMapping(value="/product/payment/list", method=RequestMethod.POST)
	public String productPaymentList()
	{
		return "product/payment/list";
	}
	
	@RequestMapping(value="/product/payment/list", method=RequestMethod.POST)
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		JSONObject retval = new JSONObject();
		JSONObject result = new JSONObject();
		UsersVO user = AttributeName.getUserData(request);
		
		try
		{
			ProductOrderVO totalItem = new ProductOrderVO();
			
			totalItem.setUserId(user.getId());
			
			
			int totalCount = ProductOrderService.getInstance().totalCount(totalItem);
			ProductPagingVO page = new ProductPagingVO(request, totalCount);
			ProductOrderVO params = new ProductOrderVO();
			
			
			params.setUserId(user.getId());
			params.setStartNo(page.getStartNo());
			params.setEndNo(page.getEndNo());
			
			List<ProductOrderVO> list = ProductOrderService.getInstance().selectList(params);
			
			
			result.put("list", list);
			retval.put("result", result);			
			retval.put("code", 0);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			retval.put("code", 0);
			retval.put("msg", exp.getMessage());
			
		}
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
