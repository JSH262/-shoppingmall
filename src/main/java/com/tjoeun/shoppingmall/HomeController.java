package com.tjoeun.shoppingmall;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.shoppingmall.service.IndexService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String homePage(Locale locale, Model model) 
	{
		return "index";
	}
	
	//consumes: mime를 지정해서 요청에 대한 데이터를 고정시킬 수 있다.
	//produces: mime를 지정해서 응답에 대한 데이터를 고정시킨다.
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String home(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		JSONObject retval = new JSONObject();
		
		try
		{
			JSONObject result = new JSONObject();		
			IndexService indexService = IndexService.getInstance();
			
			result.put("sellList", indexService.lotSellProductList());
			result.put("newList", indexService.newProductList());
			retval.put("result", result);
			retval.put("code", "0");
		}
		catch(Exception exp)
		{
			retval.put("code", "-999");
			retval.put("msg", exp.getMessage());
		}
		
		return retval.toJSONString();
	}
	
	
	
	
	
}
