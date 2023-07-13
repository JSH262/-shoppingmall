package com.tjoeun.shoppingmall.dao;

import java.util.ArrayList;
import java.util.HashMap;
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

	public List<CategoryVO> selectList(SqlSession mapper) 
	{
		System.out.println("CategoryDAO 클래스의 selectList() 메소드 실행");
		return mapper.selectList("Category.selectList");
	}
	
	public void increment(SqlSession mapper, HashMap<String, Integer> hmap) 
	{
		System.out.println("CategoryDAO 클래스의 increment() 메소드 실행");
		mapper.update("Category.increment", hmap);
	}
	
	public void insert(SqlSession mapper, CategoryVO vo) 
	{
		System.out.println("CategoryDAO 클래스의 insert() 메소드 실행");
		mapper.insert("Category.insert", vo);
	}
	
	public void reply(SqlSession mapper, CategoryVO vo) 
	{
		System.out.println("CategoryDAO 클래스의 reply() 메소드 실행");
		mapper.insert("Category.reply", vo);
	}
	
	public void delete(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 delete() 메소드 실행");
		mapper.delete("Category.delete", id);
	}
	
	public CategoryVO selectByid(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 selectByid() 메소드 실행");
		return (CategoryVO) mapper.selectOne("Category.selectByid", id);
	}
	
	public void deleteName(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 deleteName() 메소드 실행");
		mapper.update("Category.deleteName", id);
	}
	
	public void deleteCheck(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 deleteCheck() 메소드 실행");
		mapper.update("Category.deleteCheck", id);
	}
	
	public void deleteRestore(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 deleteRestore() 메소드 실행");
		mapper.update("Category.deleteRestore", id);
	}
	
	public void update(SqlSession mapper, CategoryVO vo) 
	{
		System.out.println("CategoryDAO 클래스의 update() 메소드 실행");
		mapper.update("Category.update", vo);
	}
	
	public ArrayList<CategoryVO> deleteList(SqlSession mapper, CategoryVO vo) 
	{
		System.out.println("CategoryDAO 클래스의 deleteList() 메소드 실행");
		return (ArrayList<CategoryVO>) mapper.selectList("Category.deleteList", vo);
	}
	
	public ArrayList<CategoryVO> selectGup(SqlSession mapper, int gup) 
	{
		System.out.println("CategoryDAO 클래스의 selectGup() 메소드 실행");
		return (ArrayList<CategoryVO>) mapper.selectList("Category.selectGup", gup);
	}
	
	public void reSeq(SqlSession mapper, HashMap<String, Integer> hmap) 
	{
		System.out.println("CategoryDAO 클래스의 reSeq() 메소드 실행");
		mapper.update("Category.reSeq", hmap);
	}

	public void useYn(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 useYn() 메소드 실행");
		mapper.update("Category.useYn", id);
	}

	public void createDate(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 createDate() 메소드 실행");
		mapper.update("Category.createDate", id);
	}

	public void modifyDate(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 modifyDate() 메소드 실행");
		mapper.update("Category.modifyDate", id);
	}

	public void type(SqlSession mapper, int id) 
	{
		System.out.println("CategoryDAO 클래스의 type() 메소드 실행");
		mapper.update("Category.type", id);
	}
	
}




















