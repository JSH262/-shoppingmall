package com.tjoeun.dao;

import org.apache.ibatis.session.SqlSession;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.net.aso.f;
*/

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	private UserDAO() { }
	public static UserDAO getInstance() {
		return instance;
	}
	public void insert(SqlSession mapper, UsersVO vo) {
		mapper.insert("com.tjoeun.vo.MvcBoardVO.insert", vo);
	}
	// 1이상이면 존재하는 아이디
	public int IDCheck(SqlSession mapper, String id) {
		int result = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.checkUserId", id);
		return result;

	}
	// 0: 성공, 1: 아이디 없음, 2: 비밀번호 불일치
	public int userLogin(SqlSession mapper, UsersVO vo) {
	    int result = 0;
	    
	    // 아이디 존재 여부 확인
	    int idCount = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.checkUserId", vo.getId());
	    if (idCount == 0) {
	        // 아이디가 존재하지 않는 경우
	        result = 1;
	    } else {
	        // 아이디가 존재하는 경우 비밀번호 확인
	        int passwordCount = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.passwordCheck", vo);
	        if (passwordCount == 0) {
	            // 비밀번호가 일치하지 않는 경우
	            result = 2;
	        }
	    }
	    
	    return result;
	}
	
	public void Companyinsert(SqlSession mapper, CompanyVO co) {
		mapper.insert("com.tjoeun.vo.MvcBoardVO.Companyinsert", co);
	}
	public UsersVO selectVO(SqlSession mapper, String id) {
		return (UsersVO) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.selectVO", id);
	}

}









