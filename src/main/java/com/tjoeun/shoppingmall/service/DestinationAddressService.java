package com.tjoeun.shoppingmall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.DestinationAddressDAO;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;

@Service
public class DestinationAddressService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
		
	
	
	public int insert(DestinationAddressVO vo) 
	{
		
		int retval = 0;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			DestinationAddressDAO dao = th.getMapper(DestinationAddressDAO.class);
		
			retval = dao.insert(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	public List<DestinationAddressVO> selectList(DestinationAddressVO vo) 
	{
		List<DestinationAddressVO> retval = null;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			DestinationAddressDAO dao = th.getMapper(DestinationAddressDAO.class);
		
			retval = dao.selectList(vo);
			th.commit();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	public DestinationAddressVO select(DestinationAddressVO vo) 
	{
		DestinationAddressVO retval = null;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			DestinationAddressDAO dao = th.getMapper(DestinationAddressDAO.class);
		
			retval = dao.select(vo);
			th.commit();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	public int update(DestinationAddressVO vo) 
	{
		int retval = 0;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			DestinationAddressDAO dao = th.getMapper(DestinationAddressDAO.class);
		
			retval = dao.update(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	public int delete(DestinationAddressVO vo) 
	{
		int retval = 0;
		
		try
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			DestinationAddressDAO dao = th.getMapper(DestinationAddressDAO.class);
		
			retval = dao.delete(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
}
