package com.tjoeun.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tjoeun.shoppingmall.vo.UsersVO;

public class AttributeName {
	 public final static String SESSION_USER = "user";
	 
	 static public UsersVO getUserData(HttpSession session)
	 {
		 return (UsersVO)session.getAttribute(SESSION_USER);
	 }
	 static public UsersVO getUserData(HttpServletRequest request)
	 {
		 return getUserData(request.getSession());
	 }
	 static public void setUserData(HttpSession session, UsersVO vo)
	 {
		 session.setAttribute(SESSION_USER, vo);
	 }
	 static public void setUserData(HttpServletRequest request, UsersVO vo)
	 {
		 setUserData(request.getSession(), vo);
	 }
}
