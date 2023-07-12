package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;

public class ProductOrderDAO 
{
	static ProductOrderDAO g_inst = new ProductOrderDAO();
	ProductOrderDAO() {}
	
	static public ProductOrderDAO getInstance()
	{
		return g_inst;
	}

	public int insert(SqlSession mapper, ProductOrderVO item)
	{
		return mapper.insert("ProductOrder.insert", item);
	}
	
	public long selectId(SqlSession mapper, ProductOrderVO item)
	{
		return (long) mapper.selectOne("ProductOrder.selectId", item);
	}
	
	public List<ProductOrderVO> selectList(SqlSession mapper, ProductOrderVO item)
	{
		return mapper.selectList("ProductOrder.selectList", item);
	}
	
	public int update(SqlSession mapper, ProductOrderVO item)
	{
		return mapper.update("ProductOrder.update", item);
	}
}
