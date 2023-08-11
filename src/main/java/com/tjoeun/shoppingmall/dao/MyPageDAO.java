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
	
	// 인증이 무조건 성공함 수정필요 ##################################################
	public int passwordCheck(SqlSession mapper, UsersVO vo) {
		int result = -1;

	    int passwordCount = mapper.selectOne("MyPage.passwordCehck", vo);
	    if (passwordCount > 0) {
	        result = 0;
	    } else {
	        result = 2;
	    }
	    return result;
	}


	public int passwordUpdate(SqlSession mapper, UsersVO vo) {
		int result = 0;

	    int passwordCount = (int) mapper.update("MyPage.passwordUpdate", vo);
        if (passwordCount == 0) {
            result = 3;
        }
	    return result;
	}

	public int unregister(SqlSession mapper, UsersVO vo) {
		int result = 0;
		
		int passwordCount = (int) mapper.delete("MyPage.unregister", vo);
		if (passwordCount == 0) {
			result = 1;
		}
		return result;
	}
	
	
}
