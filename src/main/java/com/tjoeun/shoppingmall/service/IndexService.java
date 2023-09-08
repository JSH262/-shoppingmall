package com.tjoeun.shoppingmall.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.IndexDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;

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
	
	public List<ProductVO> productRndList(List<Long> rowList) 
	{
		SqlSession mapper = MySession.getSession();
		IndexDAO dao = IndexDAO.getInstance();
		ProductVO params = new ProductVO();
				
		params.setRowList(rowList);
				
		return dao.productRndList(mapper, params);
	}
}
