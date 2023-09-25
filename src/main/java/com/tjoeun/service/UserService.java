package com.tjoeun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.exception.ErrorCodeException;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;

@Service
@Transactional(readOnly=true)
public class UserService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	//*/
	
	@Autowired
	UserDAO userDao;
	
	@Transactional
	public int UserInsert(UsersVO vo) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
	    int result = 0;
	    try 
	    {
	    	UserDAO userDao = th.getMapper(UserDAO.class);
	    	userDao.insert(vo);
	        th.commit();
	        result = 1; // 성공 시 1 할당
	    } 
	    catch (Exception exp) 
	    {
			log.error("", exp);
	        result = 2; // 실패 시 2 할당
	    } 
	    finally 
	    {
	    	
	    }
	    
	    return result;
	    //*/
		
		try
		{
			return userDao.insert(vo);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException(2, "userDao.insert");	
		}
	}

	// 1 이상이 넘어오면 사용불가 2는 dao 오류
	public int IDCheck(String id) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 2;
		try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
			result = dao.checkUserId(id);
	        th.commit();
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
		//*/
		
		try
		{
			return userDao.checkUserId(id);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException(2, "userDao.IDCheck");	
		}
	}
	public int userLogin(UsersVO vo) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int res = 0;
		try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
			res = dao.userLogin(vo);
	        th.commit();
		} catch (Exception e) {
			log.error("", e);
		}
		return res;
		//*/
		
		try
		{
			return userDao.userLogin(vo);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("userDao.userLogin");	
		}
	}
	
	@Transactional
	public int companyInsert(CompanyVO co) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 0;
	    try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
	        dao.companyInsert(co);
	        th.commit();
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
	        result = 2; // 실패 시 2 할당
			log.error("", e);
	    } finally {
	    }
	    return result;
	    //*/
		
		try
		{
			return userDao.companyInsert(co);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException(2, "userDao.companyInsert");	
		}
	}
	public UsersVO selectVO(String id) throws ErrorCodeException 
	{
		/*
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
    	UserDAO dao = th.getMapper(UserDAO.class);
    	
    	UsersVO retval = dao.selectVO(id);
        th.commit();
        
        return retval;
        //*/
		
		try
		{
			return userDao.selectVO(id);
		}
		catch(Exception exp)
		{
			throw new ErrorCodeException("userDao.selectVO");	
		}
	}
	

}
