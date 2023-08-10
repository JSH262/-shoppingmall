package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.AdminDAO;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class AdminService 
{
	static AdminService g_inst = new AdminService();
	AdminService() {}
	
	static public AdminService getInstance()
	{
		return g_inst;
	}
	private AdminDAO dao = AdminDAO.getInstance();
	
	public int selectCount() {
		SqlSession mapper = MySession.getSession();
		mapper.close();
		return selectCount();
		
		
	}

	public ArrayList<UsersVO> selectList(HashMap<String, Integer> hmap){
		SqlSession mapper = MySession.getSession();
		
		
		mapper.close();
		return selectList(hmap);
		
	}

	public UsersVO selectById(String id) {
		SqlSession mapper = MySession.getSession();
		
		mapper.close();
		return selectById(id);
		
	}

	public void deleteId(String id) {
		SqlSession mapper = MySession.getSession();
		
		dao.deleteId(mapper, id);
		mapper.commit();
		mapper.close();
	}

	public void update(UsersVO usersVO) {
		SqlSession mapper = MySession.getSession();
		
		dao.update(mapper, usersVO);
		mapper.commit();
		mapper.close();
	}

}
