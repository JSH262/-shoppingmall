package com.tjoeun.shoppingmall.service;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.ReviewDAO;
import com.tjoeun.shoppingmall.vo.ReviewVO;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;

public class ReviewService {
	
	private static ReviewService instance = new ReviewService();
	private ReviewService() { }
	public static ReviewService getInstance() {
		return instance;
	}
	
	private ReviewDAO dao = ReviewDAO.getInstance();
	public int ReviewInsert(ReviewVO vo) {
	    SqlSession mapper = MySession.getSession();
	    int result = 0;
	    try {
	        dao.insert(mapper, vo);
	        mapper.commit();
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // 실패 시 2 할당
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}




}
