package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.ProductVO;

public class IndexDAO 
{
	static IndexDAO g_inst = new IndexDAO();
	IndexDAO() {}
	
	static public IndexDAO getInstance()
	{
		return g_inst;
	}

	public List<ProductVO> lotSellProductList(SqlSession mapper) 
	{
		return mapper.selectList("Index.lotSellProductList");
	}

	public List<ProductVO> newProductList(SqlSession mapper) 
	{
		return mapper.selectList("Index.newProductList");
	}
	

	public List<ProductVO> productRndList(SqlSession mapper, ProductVO params) 
	{
		return mapper.selectList("Index.productRndList", params);
	}
}
