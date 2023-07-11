package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.SettingVO;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/cart/insert")
public class CartInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @Override
	public void init() throws ServletException {

		super.init();		
		this.setting = SettingService.getInstance().select();
    }

    public CartInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		
		try 
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
		catch (Exception e) 
		{
			e.printStackTrace();
			retval.put("code", -999);
			retval.put("msg", e.getMessage());
		}
		
		
		response.getWriter().write(retval.toJSONString());
		response.setContentType("application/json; charset=UTF-8");
		
	}

}
