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
	
	// 占쎌뵥筌앹빘�뵠 �눧�똻�쒎쳞占� 占쎄쉐�⑤벏釉� 占쎈땾占쎌젟占쎈툡占쎌뒄 ##################################################
	public int passwordCheck(SqlSession mapper, UsersVO vo) {
		int result;
		
	    int passwordCount = mapper.selectOne("MyPage.passwordCheck", vo);
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
		
		int count = (int) mapper.delete("MyPage.unregister", vo);
		if (count == 0) {
			result = 1;
		}
		return result;
	}

	public int userUpdate(SqlSession mapper, UsersVO vo) {
		int result = 0;

	    int count = (int) mapper.update("MyPage.userUpdate", vo);
        if (count == 0) {
            result = 1;
        }
	    return result;
	}
	
	
}
