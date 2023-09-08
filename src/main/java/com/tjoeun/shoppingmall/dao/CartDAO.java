package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;

public class CartDAO 
{
	static CartDAO g_inst = new CartDAO();
	CartDAO() {}
	
	static public CartDAO getInstance()
	{
		return g_inst;
	}

	public List<CartVO> selectList(SqlSession mapper, CartVO vo) 
	{
		return mapper.selectList("Cart.selectList", vo);
	}

	public int isRow(SqlSession mapper, CartVO vo)
	{
		return (int)mapper.selectOne("Cart.isRow", vo);
	}
	
	
	public int insert(SqlSession mapper, CartVO vo) 
	{
		return mapper.insert("Cart.insert", vo);
	}
	
	
	public int delete(SqlSession mapper, CartVO vo) 
	{
		return mapper.delete("Cart.delete", vo);
	}
	
	public int update(SqlSession mapper, CartVO vo) 
	{
		return mapper.update("Cart.update", vo);
	}

	public int count(SqlSession mapper, String userId)
	{
		return (int) mapper.selectOne("Cart.count", userId);
	}
	

	public List<CartVO> productIds(SqlSession mapper, CartVO item)
	{
		return mapper.selectList("Cart.productIds", item);
	}

	public int updateAmount(SqlSession mapper, CartVO co)
	{
		return mapper.update("Cart.updateAmount", co);
	}

	public int deleteProduct(SqlSession mapper, CartVO co)
	{
		return mapper.delete("Cart.delete", co);
	}
}
