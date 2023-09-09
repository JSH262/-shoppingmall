package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.dao.ProductOrderDAO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
public class ProductOrderService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;

	public int insert(ProductOrderVO item)
	{
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
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public long selectId(String userId)
	{
		long retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			ProductOrderVO item = new ProductOrderVO();
			
			item.setUserId(userId);
			
			retval = dao.selectId(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	public long selectId(ProductOrderVO item)
	{
		long retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			retval = dao.selectId(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public List<ProductOrderVO> selectList(ProductOrderVO item)
	{
		List<ProductOrderVO> retval = new ArrayList<>();
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.selectList(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public int update(ProductOrderVO item)
	{
		int retval = 0;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
		
			retval = dao.update(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public int totalCount(ProductOrderVO item)
	{
		int retval = 0;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.totalCount(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public ProductOrderVO select(ProductOrderVO item)
	{
		ProductOrderVO retval = null;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			ProductOrderDAO dao = th.getMapper(ProductOrderDAO.class);
			
			retval = dao.select(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}

	public boolean productOrderCancel(ProductOrderVO params, UsersVO user) 
	{
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
	}
}
