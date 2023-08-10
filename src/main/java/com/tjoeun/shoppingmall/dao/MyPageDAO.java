package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

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

	// 0: 성공, 1: 아이디 없음, 2: 비밀번호 불일치
	public int passwordCheck(SqlSession mapper, UsersVO vo) {
	    int result = 0;

	    int passwordCount = (int) mapper.selectOne("MyPage.passwordCehck", vo);
        if (passwordCount == 0) {
            result = 2;
        }
	    return result;
	}
	
	
}
