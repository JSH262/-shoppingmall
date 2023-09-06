package com.tjoeun.shoppingmall.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.ReviewDAO;
import com.tjoeun.shoppingmall.vo.ReviewVO;

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
	        result = 1; // �꽦怨� �떆 1 �븷�떦
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // �떎�뙣 �떆 2 �븷�떦
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}
	public static List<Object> selectByUserId(String userId) {
		ReviewDAO dao2 = ReviewDAO.getInstance();
		SqlSession mapper = MySession.getSession();
		
		List<Object> list = null;  
		try {
			list = dao2.selectByUserId(mapper, userId);
	        mapper.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
		
		return list;
	}




}
