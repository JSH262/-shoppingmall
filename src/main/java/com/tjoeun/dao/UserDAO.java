package com.tjoeun.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;


@Mapper
public interface UserDAO 
{
	public int insert(UsersVO vo);
	public int checkUserId(String id);
	public int userLogin(UsersVO vo);
	public int companyInsert(CompanyVO co);
	public UsersVO selectVO(String id);
}

/*
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
//*/






