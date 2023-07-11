package com.tjoeun.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;

public class CartService 
{
	static CartService g_inst = new CartService();
	CartService() {}
	
	static public CartService getInstance()
	{
		return g_inst;
	}

	public List<CartVO> selectList(CartVO vo)
	{
		List<CartVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CartDAO.getInstance().selectList(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public int count(String userId) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		try
		{
			retval = CartDAO.getInstance().count(mapper, userId);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		mapper.close();
		return retval;
	}
	
	public int insert(CartVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CartDAO.getInstance().insert(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
	
	
	public int delete(CartVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CartDAO.getInstance().delete(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public int update(CartVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CartDAO.getInstance().update(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
	
	
}
