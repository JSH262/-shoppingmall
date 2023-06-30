package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.ProductVO;

public class ProductDAO {
	static ProductDAO g_inst = new ProductDAO();
	ProductDAO() {}
	
	static public ProductDAO getInstance()
	{
		return g_inst;
	}
	
	public int insert(SqlSession mapper, ProductVO item)
	{
		return mapper.insert("Product.insert", item);
	}
	
	public List<ProductVO> selectList(SqlSession mapper)
	{
		return mapper.selectList("Product.selectList");
	}
	
}
