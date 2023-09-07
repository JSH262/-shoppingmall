package com.tjoeun.service;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.CompanyVO;

public class UserService {
	
	private static UserService instance = new UserService();
	private UserService() { }
	public static UserService getInstance() {
		return instance;
	}
	
	private UserDAO dao = UserDAO.getInstance();
	public int UserInsert(UsersVO vo) {
	    SqlSession mapper = MySession.getSession();
	    int result = 0;
	    try {
	        dao.insert(mapper, vo);
	        mapper.commit();
	        result = 1; // �꽦怨� �떆 1 �븷�떦
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // �떎�뙣 �떆 2 �븷�떦
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}

	// 1 �씠�긽�씠 �꽆�뼱�삤硫� �궗�슜遺덇� 2�뒗 dao �삤瑜�
	public int IDCheck(String id) {
		SqlSession mapper = MySession.getSession();
		int result = 2;
		try {
			result = dao.IDCheck(mapper, id);
			mapper.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 mapper.rollback();
		}
		mapper.close();
		return result;
	}
	public int userLogin(UsersVO vo) {
		SqlSession mapper = MySession.getSession();
		int res = 0;
		try {
			res = dao.userLogin(mapper, vo);
			mapper.commit();
		} catch (Exception e) {
			mapper.rollback();
		}
		mapper.close();
		return res;
	}
	public int CompanyInsert(CompanyVO co) {
		SqlSession mapper = MySession.getSession();
		int result = 0;
	    try {
	        dao.Companyinsert(mapper, co);
	        mapper.commit();
	        result = 1; // �꽦怨� �떆 1 �븷�떦
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // �떎�뙣 �떆 2 �븷�떦
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}
	public UsersVO selectVO(String id) {
		SqlSession mapper = MySession.getSession();
		return dao.selectVO(mapper, id);
	}
	public int use_yn(UsersVO vo) {
		SqlSession mapper = MySession.getSession();
		int result = 0;
		try {
			result = dao.use_yn(mapper, vo);
			mapper.commit();
		} catch (Exception e) {
			 e.printStackTrace();
			 mapper.rollback();
		}
		mapper.close();
		return result;
	}
	

}
