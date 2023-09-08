package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.ReviewVO;

public class ReviewDAO 
{
	static ReviewDAO g_inst = new ReviewDAO();
	ReviewDAO() {}
	
	static public ReviewDAO getInstance()
	{
		return g_inst;
	}
	
	public int insert(SqlSession mapper, ReviewVO vo) 
	{
		return mapper.insert("Review.insert", vo);
	}
	
	public int deleteReview(SqlSession mapper, ReviewVO vo) 
	{
		return mapper.update("Review.deleteReview", vo);
	}
	
	public List<Object> selectByUserId(SqlSession mapper, String userId) 
	{
		return mapper.selectList("Review.selectByUserId", userId);
	}

	
}
