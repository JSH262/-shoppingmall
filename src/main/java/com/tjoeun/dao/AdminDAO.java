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
		
		return mapper.selectOne("selectCount");
	}

	public List<Object> selectList(SqlSession mapper,HashMap<String, Integer> hmap){
		
		return mapper.selectList("selectList", hmap);
	}

	public UsersVO selectById(SqlSession mapper, String id) {
		
		return (UsersVO) mapper.selectList("selectById", id);		
	}

	public void deleteId(SqlSession mapper, String id) {
		mapper.update("deleteId", id);
	}

	public void update(SqlSession mapper, UsersVO usersVO) {
		mapper.update("update", usersVO);
	}

	

	
}
