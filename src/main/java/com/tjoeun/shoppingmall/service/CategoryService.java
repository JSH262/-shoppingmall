package com.tjoeun.shoppingmall.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.vo.CategoryVO;


@Service
public class CategoryService 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
		

	public List<CategoryVO> selectList(CategoryVO vo)
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		List<CategoryVO> retval = null;
		
		try
		{
			CategoryDAO dao = th.getMapper(CategoryDAO.class);
			retval = dao.selectList(vo);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	public List<CategoryVO> menu()
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		List<CategoryVO> retval = null;
		
		try
		{
			CategoryDAO dao = th.getMapper(CategoryDAO.class);
			retval = dao.menu();
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}

		return retval;
	}
	
	public List<CategoryVO> selectedMenu(CategoryVO vo)
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		List<CategoryVO> retval = null;
		
		try
		{
			CategoryDAO dao = th.getMapper(CategoryDAO.class);
			retval = dao.selectedMenu(vo);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}	
}
