package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class CartController 
{
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/cart/list", method=RequestMethod.GET)
	protected String cartList(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		UsersVO user = AttributeName.getUserData(request);
		CartVO params = new CartVO();
		
		
		params.setUserId(user.getId());

		List<CartVO> cartList = cartService.selectList(params);
		StringBuilder productIdsBuilder = new StringBuilder();
		int cartCnt = cartList.size();
	    for (int i = 0; i < cartCnt; i++) 
	    {
	        // �씠�쟾 肄붾뱶 �궡�슜
	        Long productId = cartList.get(i).getProductId();
	        productIdsBuilder.append(productId);
	        if (i != cartCnt - 1) 
	        {
	            productIdsBuilder.append(",");
	        }
	    }
		
		model.addAttribute("productIds", productIdsBuilder.toString());
		model.addAttribute("cartList", cartList);
		model.addAttribute("cartCnt", cartCnt);
		model.addAttribute("userId", user.getId());
		
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
				if(cartService.insert(request) == 1)
				{
					retval.put("code", 0);
				}
				else
				{
					retval.put("code", -1);
					retval.put("msg", "�뜲�씠�꽣瑜� ���옣�븯吏� 紐삵뻽�뒿�땲�떎.");
				}
			}
			else
			{
				retval.put("code", -999);
				retval.put("msg", "濡쒓렇�씤�씠 �븘�슂�븳 �꽌鍮꾩뒪�엯�땲�떎.");
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
		
		int res = cartService.updateAmount(co);
		if (res == 0) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("1"); // �몼
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
		
		int res = cartService.deleteProduct(co);
		if (res == 0) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("1"); // �몼
		}
	}
}
