package com.tjoeun.shoppingmall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.IndexDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;


@Service
@Transactional(readOnly=true)
public class IndexService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	IndexDAO indexDAO;
	
	public List<ProductVO> lotSellProductList() 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			IndexDAO dao = th.getMapper(IndexDAO.class);
		
			List<ProductVO> retval =  dao.lotSellProductList();
			th.commit();
			
			return retval;
		}
		catch(Exception exp)
		{
			th.rollback();	
			log.error("", exp);
		}
		
		return null;
		//*/
		
		return indexDAO.lotSellProductList();
	}

	public List<ProductVO> newProductList() 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
		
			IndexDAO dao = th.getMapper(IndexDAO.class);
		
			List<ProductVO> retval = dao.newProductList();
			th.commit();
			
			return retval;
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return null;
		//*/
		
		return indexDAO.newProductList();
	}
	
	public List<ProductVO> productRndList(List<Long> rowList) 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			IndexDAO dao = th.getMapper(IndexDAO.class);
			ProductVO params = new ProductVO();
					
			params.setRowList(rowList);
					
			List<ProductVO> retval =  dao.productRndList(params);
			th.commit();
			
			return retval;
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return null;
		//*/
		ProductVO params = new ProductVO();
		
		params.setRowList(rowList);
		
		return indexDAO.productRndList(params);
	}
}
