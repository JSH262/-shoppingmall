package com.tjoeun.shoppingmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.CategoryVO;
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
	
	public List<ProductVO> selectList(SqlSession mapper, ProductVO params)
	{
		return mapper.selectList("Product.selectList", params);
	}

	public int totalCount(SqlSession mapper, ProductVO params)
	{
		return (int)mapper.selectOne("Product.totalCount", params);
	}

	public ProductVO select(SqlSession mapper, ProductVO params) {
		return (ProductVO)mapper.selectOne("Product.select", params);
	}
	
	
	public int update(SqlSession mapper, ProductVO item) {
		return mapper.update("Product.update", item);
	}
	
	
	public List<CategoryVO> selectProductCatList(SqlSession mapper, CategoryVO params)
	{
		return mapper.selectList("Product.selectProductCatList", params);
	}

	public int updateDecrement(SqlSession mapper, ProductVO item)
	{
		return mapper.update("Product.updateDecrement", item);
	}
}
