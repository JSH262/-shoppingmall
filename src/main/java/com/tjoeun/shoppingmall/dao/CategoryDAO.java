package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.CategoryVO;

public class CategoryDAO 
{
	static CategoryDAO g_inst = new CategoryDAO();
	CategoryDAO() {}
	
	static public CategoryDAO getInstance()
	{
		return g_inst;
	}

	public List<CategoryVO> selectList(SqlSession mapper, CategoryVO vo) 
	{
		return mapper.selectList("Category.selectList", vo);
	}
	
	
	

}
