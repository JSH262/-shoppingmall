package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.dao.ProductOrderDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;

public class ProductOrderService 
{
	static ProductOrderService g_inst = new ProductOrderService();
	ProductOrderService() {}
	
	static public ProductOrderService getInstance()
	{
		return g_inst;
	}

	public int insert(ProductOrderVO item)
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		ProductOrderDAO service = ProductOrderDAO.getInstance();
		
		try
		{
			if(item.getId() == null)
			{
				// item에서 user_id가 필수이다.
				Long id = service.selectId(mapper, item);
				
				//PK 생성
				item.setId(id);
			}
						
			retval = service.insert(mapper, item);
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
	
	public long selectId(String userId)
	{
		long retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			ProductOrderVO item = new ProductOrderVO();
			
			item.setUserId(userId);
			
			retval = ProductOrderDAO.getInstance().selectId(mapper, item);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		return retval;
	}
	public long selectId(ProductOrderVO item)
	{
		long retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductOrderDAO.getInstance().selectId(mapper, item);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		return retval;
	}
	
	public List<ProductOrderVO> selectList(ProductOrderVO item)
	{
		List<ProductOrderVO> retval = new ArrayList<>();
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductOrderDAO.getInstance().selectList(mapper, item);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		return retval;
	}
	
	public int update(ProductOrderVO item)
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductOrderDAO.getInstance().update(mapper, item);
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
}
