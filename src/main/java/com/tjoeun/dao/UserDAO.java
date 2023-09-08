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
	
	public void insert(SqlSession mapper, UsersVO vo) 
	{
		mapper.insert("com.tjoeun.vo.MvcBoardVO.insert", vo);
	}
	
	public int IDCheck(SqlSession mapper, String id) 
	{
		return (int) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.checkUserId", id);
	}
	
	public int userLogin(SqlSession mapper, UsersVO vo) 
	{	    
	    return mapper.selectOne("com.tjoeun.vo.MvcBoardVO.userLogin", vo);
	}
	
	public void Companyinsert(SqlSession mapper, CompanyVO co) 
	{
		mapper.insert("com.tjoeun.vo.MvcBoardVO.Companyinsert", co);
	}
	
	public UsersVO selectVO(SqlSession mapper, String id) 
	{
		return (UsersVO) mapper.selectOne("com.tjoeun.vo.MvcBoardVO.selectVO", id);
	}
	
}









