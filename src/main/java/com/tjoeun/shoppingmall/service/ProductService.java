package com.tjoeun.shoppingmall.service;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.ProductVO;

public class ProductService {
	static ProductService g_inst = new ProductService();
	ProductService() {}
	
	static public ProductService getInstance()
	{
		return g_inst;
	}

	public int insert(ProductVO item) 
	{
		int retval = 0;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = ProductDAO.getInstance().insert(mapper, item);
			
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
