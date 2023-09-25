package com.tjoeun.shoppingmall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.MyPageDAO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
@Transactional(readOnly=true)
public class MyPageService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	MyPageDAO myPageDAO;
	
	public int passwordCheck(UsersVO vo) 
	{
		/*
		int res = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try 
		{
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			res = dao.passwordCheck(vo);
			th.commit();
		}
		catch (Exception e) 
		{
			th.rollback();
			log.error("", e);
		}
		
		return res;
		//*/
		
		return myPageDAO.passwordCheck(vo);
	}
	
	@Transactional
	public int passwordUpdate(UsersVO vo) 
	{
		/*
		int res = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try 
		{
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.passwordUpdate(vo);
	        th.commit();
		} 
		catch (Exception e) 
		{
			th.rollback();
			log.error("", e);
		}
		
		return res;
		//*/
		

		return myPageDAO.passwordUpdate(vo);
	}
	
	@Transactional
	public int unregister(UsersVO vo) 
	{
		/*
		int res = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try 
		{
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.unregister(vo);		
	        th.commit();	
		}
		catch (Exception e) 
		{
			th.rollback();
			log.error("", e);
		}
		
		return res;
		//*/
		return myPageDAO.unregister(vo);
	}
	
	@Transactional
	public int userUpdate(UsersVO vo) 
	{
		/*
		int res = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try 
		{
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.userUpdate(vo);
	        th.commit();
		} 
		catch (Exception e) 
		{
			th.rollback();
			log.error("", e);
		}
		
		return res;
		//*/
		return myPageDAO.userUpdate(vo);
	}
	
	
}
