package com.tjoeun.shoppingmall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.MyPageDAO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
public class MyPageService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
		
	public int passwordCheck(UsersVO vo) 
	{
		int res = 0;
		try 
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			res = dao.passwordCheck(vo);
			th.commit();
		}
		catch (Exception e) 
		{
			log.error("", e);
		}
		
		return res;
	}
	public int passwordUpdate(UsersVO vo) 
	{
		int res = 0;
		try 
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.passwordUpdate(vo);
	        th.commit();
		} 
		catch (Exception e) 
		{
			log.error("", e);
		}
		
		return res;
	}
	public int unregister(UsersVO vo) 
	{
		int res = 0;
		try 
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.unregister(vo);		
	        th.commit();	
		}
		catch (Exception e) 
		{
			log.error("", e);
		}
		
		return res;
	}
	public int userUpdate(UsersVO vo) 
	{
		int res = 0;
		try 
		{
			TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
			MyPageDAO dao = th.getMapper(MyPageDAO.class);
			
			res = dao.userUpdate(vo);
	        th.commit();
		} 
		catch (Exception e) 
		{
			log.error("", e);
		}
		
		return res;
	}
	
	
}
