package com.tjoeun.shoppingmall.service;

import java.util.HashMap;
import java.util.List;

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
	
	public int selectCount() {
		SqlSession mapper = MySession.getSession();
		AdminDAO dao = AdminDAO.getInstance();

		return dao.selectCount(mapper);
		
		
	}

	public List<UsersVO> selectList(HashMap<String, Integer> hmap){
		SqlSession mapper = MySession.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		
		return dao.selectList(mapper, hmap);
		
	}

	public UsersVO selectById(String id) {
		SqlSession mapper = MySession.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		
		return dao.selectById(mapper, id);
		
	}

	public void deleteId(String id) {
		SqlSession mapper = MySession.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		dao.deleteId(mapper, id);

	}

	public void update(UsersVO usersVO) {
		SqlSession mapper = MySession.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		dao.update(mapper, usersVO);

	}

}
