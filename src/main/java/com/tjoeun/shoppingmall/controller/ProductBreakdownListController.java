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
import com.tjoeun.helper.UsersType;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@WebServlet("/product/breakdown/list")
public class ProductBreakdownListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @Override
	public void init() throws ServletException {

		super.init();		
		this.setting = SettingService.getInstance().select();
    }

    public ProductBreakdownListController() {
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
				if(params == null)
					params = new ProductOrderVO();
				
				params.setSellerId(user.getId());
				params.setChoose("breakdown");
				
				Integer currentPage = params.getCurrentPage();
				Integer pageSize = params.getPageSize();
				Integer totalPage = poService.totalCount(params);
				
				currentPage = currentPage != null ? currentPage : 1;
				pageSize = pageSize != null ? pageSize : 15;
				
				ProductPagingVO page = new ProductPagingVO(currentPage, totalPage, pageSize);
				
				
				
				params.setStartNo(page.getStartNo());
				params.setEndNo(page.getEndNo());
				
				result.put("paging", page);
				result.put("list", poService.selectList(params));
				retval.put("result", result);
				retval.put("code", 0);
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
