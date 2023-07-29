package com.tjoeun.shoppingmall.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.PaymentStatus;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.PaymentService;
import com.tjoeun.shoppingmall.service.ProductOrderService;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product/payment/list")
public class ProductPaymentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SettingVO setting = null;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPaymentListController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() throws ServletException {

		super.init();
		
		this.setting = SettingService.getInstance().select();		
	}


	public String getCmd(HttpServletRequest request)
    {
    	String uri = request.getRequestURI();
    	int start = uri.lastIndexOf("/");
    	
    	return uri.substring(start + 1);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		this.doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		JSONObject retval = new JSONObject();
		JSONObject result = new JSONObject();
		UsersVO user = AttributeName.getUserData(request);
		
		try
		{
			ProductOrderVO totalItem = new ProductOrderVO();
			
			totalItem.setUserId(user.getId());
			
			
			int totalCount = ProductOrderService.getInstance().totalCount(totalItem);
			ProductPagingVO page = new ProductPagingVO(request, totalCount);
			ProductOrderVO params = new ProductOrderVO();
			
			
			params.setUserId(user.getId());
			params.setStartNo(page.getStartNo());
			params.setEndNo(page.getEndNo());

			List<ProductOrderVO> list = ProductOrderService.getInstance().selectList(params);
			
			
			result.put("list", list);
			retval.put("result", result);			
			retval.put("code", 0);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			retval.put("code", 0);
			retval.put("msg", exp.getMessage());
			
		}
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
}
