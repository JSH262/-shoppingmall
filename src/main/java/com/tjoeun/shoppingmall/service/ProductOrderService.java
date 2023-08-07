package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.tjoeun.helper.ProductOrderStatus;
import com.tjoeun.helper.Util;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.dao.ProductOrderDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.PaymentVO;
import com.tjoeun.shoppingmall.vo.ProductOrderVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

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
	
	public int totalCount(ProductOrderVO item)
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductOrderDAO.getInstance().totalCount(mapper, item);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		return retval;
	}
	
	public ProductOrderVO select(ProductOrderVO item)
	{
		ProductOrderVO retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductOrderDAO.getInstance().select(mapper, item);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
		return retval;
	}

	public boolean productOrderCancel(ProductOrderVO params, UsersVO user) 
	{
		boolean retval = false;
		SqlSession mapper = MySession.getSession();
		ProductOrderDAO poDAO = ProductOrderDAO.getInstance();
		ProductDAO pDAO = ProductDAO.getInstance();
		
		try
		{
			params.setUserId(user.getId());
		
			
			if(poDAO.update(mapper, params) == 1)
			{
				if(params.getStatus().equals(ProductOrderStatus.CANCEL.getCode()))
				{
					ProductOrderVO poVO = poDAO.select(mapper, params);
					ProductVO updateParams = new ProductVO();
					
					updateParams.setId(poVO.getProductId());
					updateParams.setChoose("amount+=n");
					updateParams.setAmount(poVO.getProductAmount());
					
					if(pDAO.update(mapper, updateParams) == 1)
					{
						retval = true;
					}
					else
					{
						throw new Exception();
					}
				}
			}
			else
			{
				throw new Exception();
			}
			
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
