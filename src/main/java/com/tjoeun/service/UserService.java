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
	        result = 1; // 占쎄쉐�⑨옙 占쎈뻻 1 占쎈막占쎈뼣
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // 占쎈뼄占쎈솭 占쎈뻻 2 占쎈막占쎈뼣
	        mapper.rollback();
	    } finally {
	        mapper.close();
	    }
	    return result;
	}

	// 1 占쎌뵠占쎄맒占쎌뵠 占쎄퐜占쎈선占쎌궎筌롳옙 占쎄텢占쎌뒠�겫�뜃占� 2占쎈뮉 dao 占쎌궎�몴占�
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
	        result = 1; // 占쎄쉐�⑨옙 占쎈뻻 1 占쎈막占쎈뼣
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = 2; // 占쎈뼄占쎈솭 占쎈뻻 2 占쎈막占쎈뼣
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
	

}
