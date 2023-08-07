package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	private static final long serialVersionUID = 1L;
    

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().invalidate();

		return "redirect:/";
	}


	@RequestMapping(value="/logout", method=RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		JSONObject result = new JSONObject();
		
		request.getSession().invalidate();
		
		result.put("redirect", request.getContextPath() + "/");
		retval.put("code", 0);
		retval.put("result", result);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
		
	}

}