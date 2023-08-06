package com.tjoeun.shoppingmall.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.DestinationAddressDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class DestinationAddressService 
{
	static DestinationAddressService g_inst = new DestinationAddressService();
	DestinationAddressService() {}
	
	static public DestinationAddressService getInstance()
	{
		return g_inst;
	}
	
	
	
	public int insert(DestinationAddressVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		DestinationAddressDAO dao = DestinationAddressDAO.getInstance();
		
		try
		{
			retval = dao.insert(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
	public List<DestinationAddressVO> selectList(DestinationAddressVO vo) 
	{
		List<DestinationAddressVO> retval = null;
		SqlSession mapper = MySession.getSession();
		DestinationAddressDAO dao = DestinationAddressDAO.getInstance();
		
		try
		{
			retval = dao.selectList(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	public DestinationAddressVO select(DestinationAddressVO vo) 
	{
		DestinationAddressVO retval = null;
		SqlSession mapper = MySession.getSession();
		DestinationAddressDAO dao = DestinationAddressDAO.getInstance();
		
		try
		{
			retval = dao.select(mapper, vo);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	public int update(DestinationAddressVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		DestinationAddressDAO dao = DestinationAddressDAO.getInstance();
		
		try
		{
			retval = dao.update(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
	public int delete(DestinationAddressVO vo) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		DestinationAddressDAO dao = DestinationAddressDAO.getInstance();
		
		try
		{
			retval = dao.delete(mapper, vo);
			mapper.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			mapper.rollback();
		}
		
		mapper.close();
				
		return retval;
	}
}
