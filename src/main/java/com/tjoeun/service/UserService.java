package com.tjoeun.service;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.mybatis.MySession;
import com.tjoeun.vo.CompanyVO;
import com.tjoeun.vo.UserVO;

public class UserService {
	
	private static UserService instance = new UserService();
	private UserService() { }
	public static UserService getInstance() {
		return instance;
	}
	
	private UserDAO dao = UserDAO.getInstance();
	public int UserInsert(UserVO vo) {
	    SqlSession mapper = MySession.getSession();
	    int result = 0;
	    try {
	        dao.insert(mapper, vo);
	        mapper.commit();
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // 실패 시 2 할당
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}

	// 1 이상이 넘어오면 사용불가 2는 dao 오류
	public int IDCheck(String userId) {
		SqlSession mapper = MySession.getSession();
		int result = 2;
		try {
			result = dao.IDCheck(mapper, userId);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		mapper.close();
		return result;
	}
	public int userLogin(UserVO vo) {
		SqlSession mapper = MySession.getSession();
		int res = 0;
		try {
			res = dao.userLogin(mapper, vo);
		} catch (Exception e) {
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
	        result = 1; // 성공 시 1 할당
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // 실패 시 2 할당
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}
	

}
