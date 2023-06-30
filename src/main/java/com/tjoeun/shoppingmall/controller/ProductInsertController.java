package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product/insertOK")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String JSP_PATH = "/WEB-INF/product/";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
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
		// 1. 상품을 추가한다.
		// 2. 상품 추가여부를 알려준다. 
		// 3. 상품 목록으로 이동한다.
		
		
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		
		response.getWriter().write("{\"key\":\"value\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
