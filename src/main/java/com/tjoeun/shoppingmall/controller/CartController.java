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

}
