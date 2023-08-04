package com.tjoeun.shoppingmall.service;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.SettingDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;

public class SettingService {
	static SettingService g_inst = new SettingService();
	SettingService() {}
	
	static public SettingService getInstance()
	{
		return g_inst;
	}
	
	public int insert(ProductVO item)
	{
		SqlSession mapper = MySession.getSession();
		int retval = 0;
		
		try
		{
			retval = SettingDAO.getInstance().insert(mapper, item);
			mapper.commit();
		}
		catch(Exception exp)
		{
			mapper.rollback();
			exp.printStackTrace();
		}
		
		mapper.close();
		
		return retval;
	}
	
	public SettingVO select()
	{
		SqlSession mapper = MySession.getSession();
		SettingVO retval = null;
		
		try
		{
			retval = SettingDAO.getInstance().select(mapper);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		
		return retval;
	}
	
}
