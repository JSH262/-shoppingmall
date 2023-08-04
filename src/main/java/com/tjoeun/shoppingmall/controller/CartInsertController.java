package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.SettingVO;

@Controller
public class CartInsertController {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @PostConstruct
	public void init() {

		this.setting = SettingService.getInstance().select();
    }
    
    
    @RequestMapping(value="/cart/list")
    public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	return "cart/list";
	}

	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
