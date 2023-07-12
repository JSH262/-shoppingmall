package com.tjoeun.shoppingmall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.dao.CategoryDAO;
import com.tjoeun.shoppingmall.dao.ProductDAO;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;

public class CategoryService 
{
	static CategoryService g_inst = new CategoryService();
	CategoryService() {}
	
	static public CategoryService getInstance()
	{
		return g_inst;
	}

	public List<CategoryVO> selectList()
	{
		List<CategoryVO> retval = null;
		SqlSession mapper = MySession.getSession();
		
		try
		{
			retval = CategoryDAO.getInstance().selectList(mapper);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
		mapper.close();
				
		return retval;
	}
	
	/**
	 * 메인 카테고리 입력 정보 insert sql 실행 호출
	 * @param vo
	 */
	public void insert(CategoryVO vo)
	{
		System.out.println("CategoryService 클래스의 insert() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().insert(mapper, vo);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 서브 카테고리 입력 정보 insert sql 실행 호출
	 * @param vo
	 */
	public void reply(CategoryVO vo)
	{
		System.out.println("CategoryService 클래스의 reply() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO dao = CategoryDAO.getInstance();
		
		vo.setLev(vo.getLev() + 1);
		vo.setSeq(vo.getSeq() + 1);
		
		HashMap<String, Integer> hmap = new HashMap<>();
		hmap.put("gup", vo.getGup());
		hmap.put("seq", vo.getSeq());
		dao.increment(mapper, hmap);
		
		dao.reply(mapper, vo);
		
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 삭제할 카테고리 1건을 삭제하는 delete sql 실행 호출
	 * @param id
	 */
	public void delete(int id) 
	{
		System.out.println("CategoryService 클래스의 delete() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().delete(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 삭제 / 수정 카테고리 1건을 얻어오는 select sql 실행 호출
	 * @param id
	 * @return
	 */
	public CategoryVO selectByid(int id) 
	{
		System.out.println("CategoryService 클래스의 selectByid() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryVO vo = CategoryDAO.getInstance().selectByid(mapper, id);
		System.out.println(vo);
		mapper.close();
		return vo;
	}
	
	/**
	 * 삭제할 카테고리 1건을 "삭제된 카테고리 입니다."로 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void deleteName(int id)
	{
		System.out.println("CategoryService 클래스의 deleteName() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().deleteName(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 삭제 Y / n 결정하는 카테고리 1건을 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void useYn(int id)
	{
		System.out.println("CategoryService 클래스의 useYn() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().useYn(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 생성일 카테고리 1건을 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void createDate(int id)
	{
		System.out.println("CategoryService 클래스의 createDate() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().createDate(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 수정날짜 카테고리 1건을 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void modifyDate(int id)
	{
		System.out.println("CategoryService 클래스의 modifyDate() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().modifyDate(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 카테고리용도(상품 게시판) 1건을 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void type(int id)
	{
		System.out.println("CategoryService 클래스의 type() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().type(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 삭제할 카테고리 1건의 deleteCheck 필드의 값을 "YES"로 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void deleteCheck(int id)
	{
		System.out.println("CategoryService 클래스의 deleteCheck() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().deleteCheck(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 복구할 카테고리 1건의 deleteCheck 필드의 값을 "NO"로 수정하는 update sql 실행 호출
	 * @param id
	 */
	public void deleteRestore(int id)
	{
		System.out.println("CategoryService 클래스의 deleteRestore() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().deleteRestore(mapper, id);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 수정할 카테고리 1건을 수정하는 update sql 실행 호출
	 * @param vo
	 */
	public void update(CategoryVO vo)
	{
		System.out.println("CategoryService 클래스의 update() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO.getInstance().update(mapper, vo);
		mapper.commit();
		mapper.close();
	}
	
	/**
	 * 삭제할 카테고리 목록을 얻어오는 select sql 실행 호출
	 * @param vo
	 * @return
	 */
	public ArrayList<CategoryVO> deleteList(CategoryVO vo)
	{
		System.out.println("CategoryService 클래스의 deleteList() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		ArrayList<CategoryVO> deleteList = CategoryDAO.getInstance().deleteList(mapper, vo);
		mapper.close();
		return deleteList;
	}
	
	/**
	 * delete.jsp에서 호출 seq를 다시 부여할 카테고리 그룹(gup)을 넘겨받고 삭제한 카테고리 그룹의 seq를 0부터 1씩 증가하게 다시 부여하는 update sql 실행 호출
	 * @param gup
	 */
	public void reSeq(int gup)
	{
		System.out.println("CategoryService 클래스의 reSeq() 메소드 실행");
		SqlSession mapper = MySession.getSession();
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryVO> gupList = dao.selectGup(mapper, gup);
		for (int i=0; i<gupList.size(); i++) 
		{
			HashMap<String, Integer> hmap = new HashMap<>();
			hmap.put("id", gupList.get(i).getId());
			hmap.put("i", i);
			dao.reSeq(mapper, hmap);
		}
		mapper.commit();
		mapper.close();
	}
	
}












