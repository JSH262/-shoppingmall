package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class MyPageDAO 
{
	static MyPageDAO g_inst = new MyPageDAO();
	MyPageDAO() {}
	
	static public MyPageDAO getInstance()
	{
		return g_inst;
	}
	
	public int passwordCheck(SqlSession mapper, UsersVO vo) 
	{
	    return mapper.selectOne("MyPage.passwordCheck", vo);
	}

	public int passwordUpdate(SqlSession mapper, UsersVO vo) 
	{
	    return mapper.update("MyPage.passwordUpdate", vo);
	}

	public int unregister(SqlSession mapper, UsersVO vo) 
	{
		return mapper.update("MyPage.unregister", vo);
	}

	public int userUpdate(SqlSession mapper, UsersVO vo) 
	{
	    return mapper.update("MyPage.userUpdate", vo);
	}
	
	
}
