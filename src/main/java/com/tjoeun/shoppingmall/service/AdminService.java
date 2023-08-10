package com.tjoeun.shoppingmall.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CartDAO;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
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
		
	}

	public ArrayList<UsersVO> selectList(SqlSession session, HashMap<String, Integer> hmap){
		SqlSession mapper = MySession.getSession();
	}

	public UsersVO selectById(String id) {
		SqlSession mapper = MySession.getSession();
		
	}

	public void deleteId(String id) {
		SqlSession mapper = MySession.getSession();
		
	}

	public void update(UsersVO usersVO) {
		SqlSession mapper = MySession.getSession();
		
	}

}
