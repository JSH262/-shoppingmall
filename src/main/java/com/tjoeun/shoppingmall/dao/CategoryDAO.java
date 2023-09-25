package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.CategoryVO;

@Mapper
public interface CategoryDAO 
{
	public List<CategoryVO> selectList(CategoryVO vo);
	public List<CategoryVO> menu();
	public List<CategoryVO> selectedMenu(CategoryVO vo);
}

/*
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
	
	public List<CategoryVO> menu(SqlSession mapper) 
	{
		return mapper.selectList("Category.menu");
	}
	public List<CategoryVO> selectedMenu(SqlSession mapper, CategoryVO vo) 
	{
		return mapper.selectList("Category.selectedMenu", vo);
	}


}
//*/