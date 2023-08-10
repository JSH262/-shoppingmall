package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.vo.CartVO;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class CartController 
{
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(value="/cart/list", method=RequestMethod.GET)
	protected String cartList(HttpServletRequest request, HttpServletResponse response)
	{
		return "cart/list";
	}
	
	@RequestMapping(value="/cart/insert", method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		
		try 
		{
			if(AttributeName.getUserData(request) != null)
			{			
				if(CartService.getInstance().insert(request) == 1)
				{
					retval.put("code", 0);
				}
				else
				{
					retval.put("code", -1);
					retval.put("msg", "데이터를 저장하지 못했습니다.");
				}
			}
			else
			{
				retval.put("code", -999);
				retval.put("msg", "로그인이 필요한 서비스입니다.");
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
	@RequestMapping(value="/cart/updateAmount", method=RequestMethod.POST)
	protected void updateAmount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		long productId = Long.parseLong(request.getParameter("productId"));
		int amount = Integer.parseInt(request.getParameter("amount"));

		CartVO co = new CartVO();
		co.setAmount(amount);
		co.setUserId(userId);
		co.setProductId(productId);
		
		int res = CartService.updateAmount(co);
		if (res == 0) {
			response.getWriter().write("0"); // ②
		} else {
			response.getWriter().write("1"); // ②
		}
	}
	
	@RequestMapping(value="/cart/deleteProduct", method=RequestMethod.POST)
	protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		long productId = Long.parseLong(request.getParameter("productId"));

		CartVO co = new CartVO();
		co.setUserId(userId);
		co.setProductId(productId);
		
		int res = CartService.deleteProduct(co);
		if (res == 0) {
			response.getWriter().write("0"); // ②
		} else {
			response.getWriter().write("1"); // ②
		}
	}
}
