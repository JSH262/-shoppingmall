package com.tjoeun.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.AdminDAO;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
public class AdminService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	
	
	public int selectCount() 
	{		
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
	}

	public List<UsersVO> selectList(HashMap<String, Integer> hmap) {
		TransactionHelper th = new TransactionHelper(sqlSession, transactionManager);
		try {
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
	}

	public UsersVO selectById(String id) {
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
	}

	public void deleteId(String id) {
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
	}

	public void updateId(UsersVO usersVO) {
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
	}

	public void restoreId(String id) {
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
	}

}