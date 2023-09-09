package com.tjoeun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.helper.TransactionHelper;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;

@Service
public class UserService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	org.mybatis.spring.SqlSessionTemplate sqlSession;

	@Autowired
	org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager;
	
	public int UserInsert(UsersVO vo) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
	    int result = 0;
	    try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
	        dao.insert(vo);
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
			log.error("", e);
	        result = 2; // 실패 시 2 할당
	    } finally {
	    }
	    return result;
	}

	// 1 이상이 넘어오면 사용불가 2는 dao 오류
	public int IDCheck(String id) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 2;
		try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
			result = dao.checkUserId(id);
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}
	public int userLogin(UsersVO vo) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int res = 0;
		try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
			res = dao.userLogin(vo);
		} catch (Exception e) {
			log.error("", e);
		}
		return res;
	}
	public int companyInsert(CompanyVO co) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
		int result = 0;
	    try {
	    	UserDAO dao = th.getMapper(UserDAO.class);
	        dao.companyInsert(co);
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
	        result = 2; // 실패 시 2 할당
			log.error("", e);
	    } finally {
	    }
	    return result;
	}
	public UsersVO selectVO(String id) {
		TransactionHelper th = new TransactionHelper(this.sqlSession, this.transactionManager);
    	UserDAO dao = th.getMapper(UserDAO.class);
    	
		return dao.selectVO(id);
	}
	

}
