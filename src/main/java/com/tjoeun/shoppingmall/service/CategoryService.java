package com.tjoeun.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;

public class CategoryService 
{
	static CategoryService g_inst = new CategoryService();
	CategoryService() {}
	
	static public CategoryService getInstance()
	{
		return g_inst;
	}

	public List<CategoryVO> selectList(CategoryVO vo)
	{
		List<CategoryVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CategoryDAO.getInstance().selectList(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public List<CategoryVO> menu()
	{
		List<CategoryVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CategoryDAO.getInstance().menu(mapper);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public List<CategoryVO> selectedMenu(CategoryVO vo)
	{
		List<CategoryVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CategoryDAO.getInstance().selectedMenu(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}	
}
