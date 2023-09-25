package com.tjoeun.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.dao.AdminDAO;
import com.tjoeun.exception.ErrorCodeException;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
@Transactional(readOnly=true)
public class AdminService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	AdminDAO adminDAO;
	
	public int selectCount() throws ErrorCodeException
	{		
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try
		{
			AdminDAO adminDAO = th.getMapper(AdminDAO.class); 
			int cnt = adminDAO.selectCount(); 
			th.commit();
			return cnt;
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);		
		}
		
		return 0;
		//*/
		
		try
		{
			return adminDAO.selectCount();
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException(0, "adminDAO.selectCount");	
		}
	}

	public List<UsersVO> selectList(HashMap<String, Integer> hmap) throws ErrorCodeException
	{
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		
		try 
		{
			AdminDAO adminDAO = th.getMapper(AdminDAO.class);			
			List<UsersVO> retval = adminDAO.selectList(hmap);
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
		
		try
		{
			return adminDAO.selectList(hmap);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("adminDAO.selectList");	
		}
	}

	public UsersVO selectById(String id) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try
		{
			AdminDAO adminDAO = th.getMapper(AdminDAO.class);
			UsersVO retval = adminDAO.selectById(id);
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
		
		try
		{
			return adminDAO.selectById(id);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("adminDAO.selectById");	
		}
	}

	@Transactional
	public void deleteId(String id) throws ErrorCodeException
	{
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try
		{
			
			AdminDAO adminDAO = th.getMapper(AdminDAO.class);
			adminDAO.deleteId(id);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		//*/
		
		try
		{
			adminDAO.deleteId(id);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("adminDAO.deleteId");	
		}
	}

	@Transactional
	public void updateId(UsersVO usersVO) throws ErrorCodeException
	{
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try
		{
			AdminDAO adminDAO = th.getMapper(AdminDAO.class);
			adminDAO.updateId(usersVO);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		//*/

		try
		{
			adminDAO.updateId(usersVO);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("adminDAO.updateId");	
		}
	}

	@Transactional
	public void restoreId(String id) throws ErrorCodeException
	{
		/*
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try
		{
			AdminDAO adminDAO = th.getMapper(AdminDAO.class);
			adminDAO.restoreId(id);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		//*/
		
		try
		{
			adminDAO.restoreId(id);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("adminDAO.restoreId");	
		}
	}

}