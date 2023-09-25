package com.tjoeun.shoppingmall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.SettingDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;

@Service
@Transactional(readOnly=true)
public class SettingService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
//*/
	@Autowired
	SettingDAO settingDAO;
	
	@Transactional
	public int insert(ProductVO item)
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int retval = 0;
		
		try
		{
			SettingDAO dao = th.getMapper(SettingDAO.class);
			
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
		
		return settingDAO.insert(item);
	}
	
	public SettingVO select()
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		SettingVO retval = null;
		
		try
		{
			SettingDAO dao = th.getMapper(SettingDAO.class);
			
			retval = dao.select();
			th.commit();
		}
		catch(Exception exp)
		{
	    	th.rollback();
			log.error("", exp);
		}
		
		
		return retval;
		//*/
		
		return settingDAO.select();
	}
	
}
