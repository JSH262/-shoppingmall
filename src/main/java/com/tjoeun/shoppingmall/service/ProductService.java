package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductPagingVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.ReviewVO;

@Service
public class ProductService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;

	public int insert(ProductVO item) 
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.insert(item);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
				
		return retval;
	}
	
	/*
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
	//*/
	public List<ProductVO> selectList(ProductVO params, ProductPagingVO page)
	{
		List<ProductVO> retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			params.setStartNo(page.getStartNo());
			params.setEndNo(page.getEndNo());
			
			retval = dao.selectList(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	/*
	public int totalCount(java.util.HashMap<String, Object> params)
	{
		int retval = 0;
		
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
	//*/
	public int totalCount(ProductVO params)
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.totalCount(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	public ProductVO select(Long id)
	{
		ProductVO params = new ProductVO();
		
		params.setId(id);
		
		return this.select(params);
	}
	
	
	public ProductVO select(ProductVO params)
	{
		ProductVO retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.select(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	public int update(ProductVO item) 
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.update(item);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		
	}

	public List<ProductVO> selectList(List<CartVO> productIds) 
	{
		List<ProductVO> retval = new ArrayList<>();
		ProductVO params = new ProductVO();
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			params.setChoose("detail");
			for(int i = 0; i<productIds.size(); i++)
			{
				try
				{
					ProductDAO dao = th.getMapper(ProductDAO.class);
					params.setId(productIds.get(i).getProductId());
					
					ProductVO result = dao.select(params);
					
					retval.add(result);
				}
				catch(NumberFormatException  nfexp)
				{
					
				}
			}
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	public List<CategoryVO> selectProductCatList(CategoryVO params)
	{
		List<CategoryVO> retval = new ArrayList<>();
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.selectProductCatList(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	public List<ReviewVO> selectProductReview(ProductVO params)
	{
		List<ReviewVO> retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.selectProductReview(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	public Integer selectProductReviewCount(ProductVO params)
	{
		Integer retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.selectProductReviewCount(params);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	public String totalProductSellPrice(String sellerId)
	{
		ProductVO params = new ProductVO();
		String retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		params.setSellerId(sellerId);
		
		try
		{
			ProductDAO dao = th.getMapper(ProductDAO.class);
			
			retval = dao.totalProductSellPrice(params);
			//th.commit();
		}
		catch(Exception exp)
		{
			//th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
}
