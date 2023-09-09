package com.tjoeun.shoppingmall;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.CategoryService;
import com.tjoeun.shoppingmall.service.IndexService;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	IndexService indexService;
	
	@Autowired
	ProductService productService;

	

	@Autowired	
	CategoryService categoryService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String homePage(Locale locale, Model model, HttpSession session) 
	{
		//*
		UsersVO userInfo = AttributeName.getUserData(session);
		
		if(userInfo != null && userInfo.getType().equals(UsersType.SELLER))
		{
			return "redirect:/product/list";
		}
		else		
		{
			if(session.getAttribute("categoryList") == null)
			{
				List<CategoryVO> catMenu = categoryService.menu();
				HashMap<Integer, List<CategoryVO>> catDownList1 = new HashMap<Integer, List<CategoryVO>>();
				HashMap<Integer, List<CategoryVO>> catDownList2 = new HashMap<Integer, List<CategoryVO>>();
				
				for(int i = 0; i<catMenu.size(); i++)
				{
					CategoryVO tmp = catMenu.get(i);					
					List<CategoryVO> tmpValue = categoryService.selectedMenu(tmp);
					
					catDownList1.put(tmp.getId(), tmpValue);
					
					
					
					for(int q = 0; q<tmpValue.size(); q++)
					{
						CategoryVO tmp2 = tmpValue.get(q);
						List<CategoryVO> tmp2Value = categoryService.selectedMenu(tmp2);
												
						catDownList2.put(tmp2.getId(), tmp2Value);
					}
					
					
				}
				
				
				
				
				
				String retval = new Gson().toJson(catDownList1);				
				session.setAttribute("categoryDownList", retval);
				session.setAttribute("categoryDownList2", new Gson().toJson(catDownList2));
				
				session.setAttribute("categoryList", catMenu);
			}
			
			return "index";
		}
		//*/
		
		//return "index";
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
			int productTotalCount = productService.totalCount(null); 
			
			
			List<ProductVO> sellList = indexService.lotSellProductList();
			if(sellList.size() > 0)
				result.put("sellList", sellList);
			else
			{
				ArrayList<Long> rows = new ArrayList<Long>(); 
				
				for(int i = 0; i<5; i++)
				{
					Long tmp = (long)(Math.random() * productTotalCount);
					
					if(rows.contains(tmp))
						i -= 1;
					else
						rows.add(tmp);
				}
				
				result.put("rndList", indexService.productRndList(rows));
			}
			
			List<ProductVO> newList = indexService.newProductList();
			if(newList.size() > 0)
				result.put("newList", newList);
			else
			{
				ArrayList<Long> rows = new ArrayList<Long>(); 
				
				for(int i = 0; i<6; i++)
				{
					Long tmp = (long)(Math.random() * productTotalCount);
					
					if(rows.contains(tmp))
						i -= 1;
					else
						rows.add(tmp);
				}
				
				result.put("rndList2", indexService.productRndList(rows));
			}
			
			
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
	
	
	@RequestMapping(value = "/chatting", method = {RequestMethod.GET, RequestMethod.POST})
	public String chatting(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		UsersVO userInfo = AttributeName.getUserData(request);
		
		if(userInfo != null && userInfo.getType().equals(UsersType.BUYER))
		{
			return "chattingBuyer";
		}
		else if(userInfo != null && userInfo.getType().equals(UsersType.SELLER))
		{
			return "chattingSeller";
		}
		
		return null;
	}
	@RequestMapping(value="/alertTest", method=RequestMethod.GET)
	public String alert(HttpSession session)
	{
		/*
		UsersVO userInfo = AttributeName.getUserData(session);
		
		if(userInfo != null && userInfo.getType().equals(UsersType.SELLER))
		{
			return "realtimeAlert";
		}
		
		return "redirect:/";
		*/
		
		return "realtimeAlertTest";
	}
	
	@ResponseBody
	@RequestMapping(value="/error/login", produces="application/json;charset=UTF-8")
	public String error()
	{
		JSONObject retval = new JSONObject();
		
		retval.put("msg", "로그인이 필요합니다.");
		retval.put("code", -10000);
		
		
		return retval.toJSONString();
	}
	
}
