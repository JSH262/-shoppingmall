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
		return mapper.update("Cart.delete", vo);
	}
	

}
