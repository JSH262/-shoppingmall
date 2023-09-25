package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.dao.ProductOrderDAO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
@Transactional(readOnly=true)
public class ProductOrderService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	ProductOrderDAO productOrderDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Transactional
	public int insert(ProductOrderVO item)
	{
		/*
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			if(item.getId() == null)
			{
				// item에서 user_id가 필수이다.
				Long id = dao.selectId(item);
				
				//PK 생성
				item.setId(id);
			}
						
			retval = dao.insert(item);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		if(item.getId() == null)
		{
			// item에서 user_id가 필수이다.
			Long id = productOrderDAO.selectId(item);
			
			//PK 생성
			item.setId(id);
		}

		return productOrderDAO.insert(item);
	}
	
	public long selectId(String userId)
	{
		/*
		long retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			ProductOrderVO item = new ProductOrderVO();
			
			item.setUserId(userId);
			
			retval = dao.selectId(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		ProductOrderVO item = new ProductOrderVO();
		
		item.setUserId(userId);
		
		return productOrderDAO.selectId(item);
	}
	public long selectId(ProductOrderVO item)
	{
		/*
		long retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			retval = dao.selectId(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		return productOrderDAO.selectId(item);
	}
	
	public List<ProductOrderVO> selectList(ProductOrderVO item)
	{
		/*
		List<ProductOrderVO> retval = new ArrayList<>();
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.selectList(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		return productOrderDAO.selectList(item);
	}

	@Transactional
	public int update(ProductOrderVO item)
	{
		/*
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
		
			retval = dao.update(item);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		return productOrderDAO.update(item);
	}
	
	public int totalCount(ProductOrderVO item)
	{
		/*
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.totalCount(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		return productOrderDAO.totalCount(item);
	}
	
	public ProductOrderVO select(ProductOrderVO item)
	{
		/*
		ProductOrderVO retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.select(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
		//*/
		
		return productOrderDAO.select(item);
	}

	@Transactional
	public boolean productOrderCancel(ProductOrderVO params, UsersVO user) throws Throwable
	{
		/*
		boolean retval = false;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		ProductOrderDAO poDAO = th.getMapper(ProductOrderDAO.class);
		ProductDAO pDAO = th.getMapper(ProductDAO.class);
		
		try
		{
			params.setUserId(user.getId());
		
			
			if(poDAO.update(params) == 1)
			{
				if(params.getStatus().equals(ProductOrderStatus.CANCEL.getCode()))
				{
					ProductOrderVO poVO = poDAO.select(params);
					ProductVO updateParams = new ProductVO();
					
					updateParams.setId(poVO.getProductId());
					updateParams.setChoose("amount+=n");
					updateParams.setAmount(poVO.getProductAmount());
					
					if(pDAO.update(updateParams) == 1)
					{
						th.commit();
						retval = true;
					}
					else
					{
						throw new Exception();
					}
				}
			}
			else
			{
				throw new Exception();
			}
			
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);			
		}
		
		return retval;
		//*/
		
		boolean retval = false;
		ProductOrderDAO poDAO = this.productOrderDAO;
		ProductDAO pDAO = this.productDAO;
		
		params.setUserId(user.getId());
		
		if(poDAO.update(params) == 1)
		{
			if(params.getStatus().equals(ProductOrderStatus.CANCEL.getCode()))
			{
				ProductOrderVO poVO = poDAO.select(params);
				ProductVO updateParams = new ProductVO();
				
				updateParams.setId(poVO.getProductId());
				updateParams.setChoose("amount+=n");
				updateParams.setAmount(poVO.getProductAmount());
				
				if(pDAO.update(updateParams) == 1)
				{
					retval = true;
				}
				else
				{
					throw new Throwable();
				}
			}
		}
		else
		{
			throw new Throwable();
		}
		
		return retval;
	}
}
