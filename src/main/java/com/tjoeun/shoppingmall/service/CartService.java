package com.tjoeun.shoppingmall.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Service
public class CartService 
{	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
		
	public List<CartVO> selectList(CartVO vo)
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		List<CartVO> retval = null;
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			
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
	
	public int count(String userId) 
	{
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int retval = 0;
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			retval = dao.count(userId);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	
	public int insert(CartVO vo) 
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			retval = dao.insert(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
						
		return retval;
	}
	
	
	public int delete(CartVO vo) 
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			retval = dao.delete(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
				
		return retval;
	}
	
	public int update(CartVO vo) 
	{
		int retval = 0;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			retval = dao.update(vo);
	        th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}

	public int insert(HttpServletRequest request) throws JsonSyntaxException, IOException, ParseException 
	{
		int retval = 0;
		UsersVO user = AttributeName.getUserData(request);
		String userId = user.getId();
		CartVO vo = new Gson().fromJson(com.tjoeun.helper.Util.toBody(request), CartVO.class);
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			ProductVO pVo = new ProductVO();			
			pVo.setId(vo.getProductId());
			vo.setUserId(userId);
			
			if(dao.isRow(vo) > 0)
			{
				retval = dao.update(vo);
			}
			else				
			{
				ProductDAO productDao = th.getMapper(ProductDAO.class);
				String sellerId = productDao.select(pVo).getSellerId();
		
				vo.setSellerId(sellerId);
				
				retval = dao.insert(vo);
				th.commit();
			}
			
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
		
		return retval;
	}
	public List<CartVO> productIds(CartVO item)
	{
		List<CartVO> retval = null;
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		
		try
		{
			CartDAO dao = th.getMapper(CartDAO.class);
			retval = dao.productIds(item);
			th.commit();
		}
		catch(Exception exp)
		{
			th.rollback();
			log.error("", exp);
		}
						
		return retval;
	}

	public int updateAmount(CartVO co) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 0;
		try {
			CartDAO dao = th.getMapper(CartDAO.class);
			dao.updateAmount(co);
	        th.commit();
			result = 0;
		} catch (Exception e) {
			th.rollback();
			result = 1;
			log.error("", e);
		} finally {
		}
		return result;
	}

	public int deleteProduct(CartVO co) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 0;
		try {
			CartDAO dao = th.getMapper(CartDAO.class);
			dao.deleteProduct(co);
	        th.commit();
			result = 0;
		} catch (Exception e) {
			th.rollback();
			result = 1;
			log.error("", e);
		} finally {
		}
		return result;
	}
}
