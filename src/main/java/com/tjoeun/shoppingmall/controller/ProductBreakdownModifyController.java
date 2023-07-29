package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.UsersType;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ImageController/product/breakdown/status
 */
@WebServlet("/product/breakdown/modify")
public class ProductBreakdownModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @Override
	public void init() throws ServletException {

		super.init();		
		this.setting = SettingService.getInstance().select();
    }

    public ProductBreakdownModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		ProductOrderService poService = ProductOrderService.getInstance();
		
		try 
		{
			UsersVO user = AttributeName.getUserData(request);
			if(user != null && UsersType.SELLER.equals(user.getType()))
			{
				JSONObject result = new JSONObject();
				ProductOrderVO params = new Gson().fromJson(Util.toBody(request), ProductOrderVO.class);
				if(params != null)
				{
					if(params.getStatus() >= 1 && params.getStatus() <=3 )
					{
						params.setSellerId(user.getId());
						
						if(poService.update(params) == 1)
						{
							retval.put("code", 0);	
						}
						else
						{
							retval.put("code", -997);
							retval.put("msg", "error");
						}
					}
					else
					{
						retval.put("code", -995);
						retval.put("msg", "error");
					}
				}
				else
				{
					retval.put("code", -998);
					retval.put("msg", "error");
				}
			}
			else if(user != null && UsersType.BUYER.equals(user.getType()))
			{
				ProductOrderVO params = new Gson().fromJson(Util.toBody(request), ProductOrderVO.class);
				if(params.getStatus().equals(ProductOrderStatus.CANCEL.getCode()))
				{
					if(poService.productOrderCancel(params, user))
					{
						retval.put("code", 0);
					}
					else
					{
						retval.put("code", -995);
						retval.put("msg", "error");
					}	
				}
				else if(params.getStatus().equals(ProductOrderStatus.NML_DEAL_COMPLETE.getCode()))
				{
					params.setUserId(user.getId());
					
					if(poService.update(params) == 1)
					{
						retval.put("code", 0);
					}
					else
					{
						retval.put("code", -994);
						retval.put("msg", "error");
					}
					
				}
					
			}
			else
			{
				retval.put("code", -996);
				retval.put("msg", "error");
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
