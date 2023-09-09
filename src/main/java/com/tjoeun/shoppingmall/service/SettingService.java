package com.tjoeun.shoppingmall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.SettingDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;

@Service
public class SettingService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;

	
	public int insert(ProductVO item)
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int retval = 0;
		
		try
		{
			SettingDAO dao = th.getMapper(SettingDAO.class);
			
			retval = dao.insert(item);
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		return retval;
	}
	
	public SettingVO select()
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		SettingVO retval = null;
		
		try
		{
			SettingDAO dao = th.getMapper(SettingDAO.class);
			
			retval = dao.select();
		}
		catch(Exception exp)
		{
			log.error("", exp);
		}
		
		
		return retval;
	}
	
}
