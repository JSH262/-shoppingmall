package com.tjoeun.shoppingmall.dao;

import java.util.List;

import com.tjoeun.shoppingmall.vo.CartVO;


public interface CartDAO 
{
	public List<CartVO> selectList(CartVO vo);
	public int isRow(CartVO vo);
	public int insert(CartVO vo);
	public int delete(CartVO vo);
	public int update(CartVO vo);
	public int count(String userId);
	public List<CartVO> productIds(CartVO item);
	public int updateAmount(CartVO co);
	public int deleteProduct(CartVO co);
}

/*
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
//*/
