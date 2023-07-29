package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
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
	public ProductVO select(ProductVO params)
	{
		ProductVO retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().select(mapper, params);
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

	public List<ProductVO> selectList(List<CartVO> productIds) 
	{
		List<ProductVO> retval = new ArrayList<>();
		SqlSession mapper = MySession.getSession();
		ProductVO params = new ProductVO();
		
		try
		{
			params.setChoose("detail");
			for(int i = 0; i<productIds.size(); i++)
			{
				try
				{
					params.setId(productIds.get(i).getProductId());
					
					ProductVO result = ProductDAO.getInstance().select(mapper, params);
					
					retval.add(result);
				}
				catch(NumberFormatException  nfexp)
				{
					
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	public List<CategoryVO> selectProductCatList(CategoryVO params)
	{
		List<CategoryVO> retval = new ArrayList<>();
		SqlSession mapper = MySession.getSession();
		ProductDAO dao = ProductDAO.getInstance(); 
		
		try
		{
			retval = dao.selectProductCatList(mapper, params);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
}
