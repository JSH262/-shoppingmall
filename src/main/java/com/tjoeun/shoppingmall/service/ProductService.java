package com.tjoeun.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;

public class ProductService 
{
	static ProductService g_inst = new ProductService();
	ProductService() {}
	
	static public ProductService getInstance()
	{
		return g_inst;
	}

	public int insert(ProductVO item) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().insert(mapper, item);
			
			mapper.commit();
		}
		catch(Exception exp)
		{
			mapper.rollback();
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public List<ProductVO> selectList(HashMap<String, Object> params, ProductPagingVO page)
	{
		
		
		List<ProductVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			if(params == null)
			{
				params = new HashMap<>();
			}

			params.put("startNo", page.getStartNo());
			params.put("endNo", page.getEndNo());
			
			retval = ProductDAO.getInstance().selectList(mapper, params);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public int totalCount(java.util.HashMap<String, Object> params)
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().totalCount(mapper, params);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	public ProductVO select(int id)
	{
		ProductVO retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().select(mapper, id);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	public int update(ProductVO item) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().update(mapper, item);
			mapper.commit();
		}
		catch(Exception exp)
		{
			mapper.rollback();
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
		
	}
}
