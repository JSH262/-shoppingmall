package com.tjoeun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.UsersVO;


public interface AdminDAO {
		
	public int selectCount();

	public List<UsersVO> selectList(HashMap<String, Integer> hmap);

	public UsersVO selectById(String id);

	public void deleteId(String id);

	public void updateId(UsersVO usersVO);
	public void restoreId(String id) ;

	

	
}