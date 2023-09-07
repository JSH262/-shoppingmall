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
	// 1�씠�긽�씠硫� 議댁옱�븯�뒗 �븘�씠�뵒
	public int IDCheck(SqlSession mapper, String id) {
		int result = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.checkUserId", id);
		return result;

	}
	// 0: �꽦怨�, 1: �븘�씠�뵒 �뾾�쓬, 2: 鍮꾨�踰덊샇 遺덉씪移�
	public int userLogin(SqlSession mapper, UsersVO vo) {
	    int result = 0;
	    
	    // �븘�씠�뵒 議댁옱 �뿬遺� �솗�씤
	    int idCount = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.checkUserId", vo.getId());
	    if (idCount == 0) {
	        // �븘�씠�뵒媛� 議댁옱�븯吏� �븡�뒗 寃쎌슦
	        result = 1;
	    } else {
	        // �븘�씠�뵒媛� 議댁옱�븯�뒗 寃쎌슦 鍮꾨�踰덊샇 �솗�씤
	        int passwordCount = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.passwordCheck", vo);
	        if (passwordCount == 0) {
	            // 鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒗 寃쎌슦
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
	public int use_yn(SqlSession mapper, UsersVO vo) {
		int result = (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.use_yn", vo);
		return result;
	}

}









