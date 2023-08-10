package com.tjoeun.shoppingmall.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.IndexDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class IndexService 
{
	static IndexService g_inst = new IndexService();
	IndexService() {}
	
	static public IndexService getInstance()
	{
		return g_inst;
	}

	public List<ProductVO> lotSellProductList() 
	{
		SqlSession mapper = MySession.getSession();
		IndexDAO dao = IndexDAO.getInstance(); 
		
		return dao.lotSellProductList(mapper);
	}

	public List<ProductVO> newProductList() 
	{
		SqlSession mapper = MySession.getSession();
		IndexDAO dao = IndexDAO.getInstance(); 
		
		return dao.newProductList(mapper);
	}
}
