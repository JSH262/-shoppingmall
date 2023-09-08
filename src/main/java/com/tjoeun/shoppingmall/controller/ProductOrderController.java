package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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
import com.tjoeun.shoppingmall.vo.UsersVO;


@Controller
public class ProductOrderController 
{
	private static final long serialVersionUID = 1L;

	
	@RequestMapping(value="/product/order", method=RequestMethod.GET)
	protected String productOrderList(HttpServletRequest request, HttpServletResponse response)
	{
		return "product/order";
	}
	
	
	
	@RequestMapping(value="/product/order", method=RequestMethod.POST)
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		/*
		Object[] productIds = null;
		
		if("POST".equals(request.getMethod().toUpperCase()))
		{
			JSONObject params = com.tjoeun.helper.Util.toJSONObject(request);			
			if(params != null)
			{
				JSONArray arr = (JSONArray)params.get("productId");
				if(arr != null && arr.size() > 0)
				{
					List<String> tmpProductIds = new ArrayList<>();
					
					for(int i = 0; i<arr.size(); i++)
					{
						tmpProductIds.add(arr.get(i).toString());
					}
					
					productIds = tmpProductIds.toArray();
				}
			}
		}
		else if("GET".equals(request.getMethod().toUpperCase()))
		{
			productIds = request.getParameterValues("productId");
		}
		//*/
		/*
		Object[] productIds = request.getParameterValues("productId");
		if(productIds == null)
		{
			JSONObject params = com.tjoeun.helper.Util.toJSONObject(request);			
			if(params != null)
			{
				JSONArray arr = (JSONArray)params.get("productId");
				if(arr != null && arr.size() > 0)
				{
					List<String> tmpProductIds = new ArrayList<>();
					
					for(int i = 0; i<arr.size(); i++)
					{
						tmpProductIds.add(arr.get(i).toString());
					}
					
					productIds = tmpProductIds.toArray();
				}
			}
		}
		*/
		UsersVO user = AttributeName.getUserData(request);
		if(user != null)
		{
			CartVO params = new CartVO();
			
			params.setUserId(user.getId());
			
			List<CartVO> productIds = CartService.getInstance().selectList(params);
			
			if(productIds != null)
			{
				JSONObject result = new JSONObject();
				DecimalFormat numFormat = new DecimalFormat("#,###");
				
				long totalPrice = 0L;
				long totalDiscountPrice = 0L;
				int totalDevliveryPrice = 0;
				
				for(CartVO item : productIds)
				{
					if(item.getAmount() <= item.getProductAmount() && item.getProductAmount() > 0)
					{
						int price = item.getAmount() * item.getPrice();
						int discountPrice = item.getAmount() * item.getDiscountPrice();
						int deliveryPrice = item.getDeliveryPrice();
						
						totalPrice += price;
						totalDiscountPrice += discountPrice;
						totalDevliveryPrice += deliveryPrice;
					}
				}
				
				result.put("fmtResultPrice", numFormat.format(totalPrice)); // 할인전 가격
				result.put("fmtResultDiscount", numFormat.format(totalPrice - totalDiscountPrice)); // 할인된 가격
				result.put("fmtResultDevliveryPrice", numFormat.format(totalDevliveryPrice)); //총 배송비
				result.put("fmtResultDiscountPrice", numFormat.format(totalDiscountPrice + totalDevliveryPrice)); //할인한 가격
				result.put("list", productIds);
				retval.put("result", result);
				retval.put("code", 0);
			}
			else
			{
				retval.put("code", -999);
				retval.put("msg", "parameter is empty");
			}
		}
		else
		{
			retval.put("code", -998);
			retval.put("msg", "error");
		}
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
