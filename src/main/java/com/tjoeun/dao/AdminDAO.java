package com.tjoeun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.UsersVO;


public class AdminDAO {
	
	private static AdminDAO instance = new AdminDAO();
	private AdminDAO() { }
	public static AdminDAO getInstance() {
		return instance;
	}
	
	public int selectCount(SqlSession mapper) {
		
		return mapper.selectOne("AdminDAO.selectCount");
	}

	public List<UsersVO> selectList(SqlSession mapper, HashMap<String, Integer> hmap){
		
		return mapper.selectList("AdminDAO.selectList", hmap);
	}

	public UsersVO selectById(SqlSession mapper, String id) {
		
		return (UsersVO) mapper.selectOne("AdminDAO.selectById", id);		
	}

	public void deleteId(SqlSession mapper, String id) {
		 mapper.update("AdminDAO.deleteId", id);
	}

	public void updateId(SqlSession mapper, UsersVO usersVO) {
		System.out.println("AdminDAO usersVO: "+ usersVO);
		mapper.update("AdminDAO.updateId", usersVO);
	}
	public void restoreId(SqlSession mapper, String id) {
		mapper.update("AdminDAO.restoreId", id);
		
	}

	

	
}
