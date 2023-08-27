package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class ProductListController 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductListController() {
		super();
	}

	 
	
	
	@RequestMapping(value="/product/list", method=RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		UsersVO vo = AttributeName.getUserData(request);
		String retval = "product/list/buyer";
		
		model.addAttribute("currentPage", Util.toPosNum(request.getParameter("currentPage"), 1));
		model.addAttribute("pageSize", Util.toPosNum(request.getParameter("pageSize"), 15));		
		model.addAttribute("productCategoryId", Util.toLong(request.getParameter("productCategoryId"), null));

		if(vo != null && UsersType.SELLER.equals(vo.getType()))
		{
			retval = "product/list/seller";
		}
		
		return retval;
	}

	
	@RequestMapping(value="/product/list", method=RequestMethod.POST)
	void doAction(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try 
		{
			final String USERS_TYPE_SELLER = UsersType.SELLER;
			final String USERS_TYPE_BUYER = UsersType.BUYER;
			UsersVO user =  AttributeName.getUserData(request);
			String sellerId = null;
			String type = null;
			
			if(user != null)
			{
				sellerId = user.getId();
				type = user.getType();	
			}
			
			
			ProductVO serviceParams = new ProductVO();
			ProductService service = ProductService.getInstance();
			JSONObject params = com.tjoeun.helper.Util.toJSONObject(request);
			long currentPage = 1;
			long pageSize = 15;

			if (params != null) 
			{
				// POST
				currentPage = (long) params.get("currentPage");
				pageSize = (long) params.get("pageSize");
				String productName = (String)params.get("productName");
				Integer productCategoryId = Util.toInt((String)params.get("productCategoryId"), null);
				
				serviceParams.setName(productName);
				serviceParams.setCategoryId(productCategoryId);
				
				
				
				/*
				currentPage = (long) params.get("currentPage");
				pageSize = (long) params.get("pageSize");
				String searchValue = (String) params.get("searchValue");
				String searchCategory = (String) params.get("searchCategory");

				if (searchValue != null && searchValue.length() > 0) 
				{
					switch (searchCategory) 
					{
					case "categoryId":
						serviceParams.put("categoryId", searchValue);
						break;

					case "name":
						serviceParams.put("name", searchValue);
						break;
					}
				}
				else 
				{
					searchValue = null;
					searchCategory = null;
				}
				//*/
			} 
			else 
			{
				/*
				// GET
				try 
				{
					ProductPagingVO page = new ProductPagingVO(request);

					currentPage = (long) page.getCurrentPage();
					pageSize = (long) page.getPageSize();
				}
				catch (Exception e) 
				{
				}
				//*/
			}

			// 판매자
			if (USERS_TYPE_SELLER.equals(type)) 
			{
				serviceParams.setChoose("list");
				serviceParams.setSellerId(sellerId);
			}

			// 구매자
			else if (USERS_TYPE_BUYER.equals(type)) 
			{
				serviceParams.setChoose("detail");
			}

			// 로그인을 하지 않은 사용자
			else 
			{
				serviceParams.setChoose("detail");
			}
			

			int totalCount = service.totalCount(serviceParams);
			ProductPagingVO page = new ProductPagingVO((int) currentPage, totalCount, (int) pageSize);
			List<ProductVO> list = service.selectList(serviceParams, page);
			JSONObject retval = new JSONObject();
			JSONObject result = new JSONObject();

			result.put("list", list);
			result.put("paging", page);
			retval.put("result", result);
			retval.put("code", 0);

			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(retval.toJSONString());
		} 
		catch (IOException exp) 
		{
			exp.printStackTrace();
		}
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//	  
//		//product 리스트 조회 -> JSON 변환 -> 전송 
//		HashMap<String, Object> params = new HashMap<>();
//		int currentPage = 1; 
//		int pageSize = 15;
//		  
//		try 
//		{ 
//			ProductPagingVO pageParam = new ProductPagingVO(request);
//			  
//			currentPage = pageParam.getCurrentPage(); pageSize = pageParam.getPageSize();
//		} 
//		catch (Exception e) 
//		{ 
//			e.printStackTrace(); 
//		}
//			  
//		params.put("choose", "list");
//		  
//		ProductService service = ProductService.getInstance(); 
//		int totalCount = service.totalCount(null); 
//		ProductPagingVO page = new ProductPagingVO(currentPage, totalCount, pageSize); 
//		List<ProductVO> list = service.selectList(params, page); 
//		JSONObject retval = new JSONObject();
//		JSONObject result = new JSONObject();
//		  
//		result.put("list", list); result.put("paging", page); retval.put("result",
//		result); retval.put("code", 0);
//		  
//		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().write(retval.toJSONString()); 
//	  }
//		  
//	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException 
//	  { 
//		  try 
//		  {
//			  HashMap<String, Object> serviceParams = new HashMap<>(); 
//			  ProductService service = ProductService.getInstance(); 
//			  JSONObject params = com.tjoeun.helper.Util.toJSONObject(request); 
//			  long currentPage = (long)params.get("currentPage"); 
//			  long pageSize = (long)params.get("pageSize");
//			  
//			  serviceParams.put("choose", "list");
//			  serviceParams.put("name", params.get("name")); 
//			  serviceParams.put("categoryId", params.get("categoryId"));
//			  
//			  int totalCount = service.totalCount(serviceParams); 
//			  ProductPagingVO page = new ProductPagingVO((int)currentPage, totalCount, (int)pageSize);
//			  List<ProductVO> list = service.selectList(serviceParams, page); 
//			  JSONObject retval = new JSONObject(); 
//			  JSONObject result = new JSONObject();
//			  
//			  result.put("list", list); 
//			  result.put("paging", page); 
//			  retval.put("result", result); 
//			  retval.put("code", 0);
//			  
//			  response.setContentType("application/json; charset=UTF-8");
//			  response.getWriter().write(retval.toJSONString());
//		  } 
//		  catch(IOException exp) 
//		  {
//			  exp.printStackTrace(); 
//		  } 
//	  }
	  
}
