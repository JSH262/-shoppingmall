package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.shoppingmall.vo.UsersVO;


public interface MyBatisDAO {
	
	int selectCount();

	ArrayList<UsersVO> selectList(HashMap<String, Integer> hmap);

	UsersVO selectById(String id);

	void deleteId(String id);

	void update(UsersVO usersVO);

	

	
}
