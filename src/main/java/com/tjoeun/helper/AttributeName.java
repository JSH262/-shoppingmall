package com.tjoeun.helper;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
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
	 
	 static public void setDestAddr(HttpSession session, DestinationAddressVO item)
	 {
		session.setAttribute("destinationAddress", item);		 
	 }
	 static public DestinationAddressVO getDestAddr(HttpSession session)
	 {
		return (DestinationAddressVO)session.getAttribute("destinationAddress");		 
	 }

	 static public void setDestAddr(HttpServletRequest request, DestinationAddressVO item)
	 {
		setDestAddr(request.getSession(), item);		 
	 }
	 static public DestinationAddressVO getDestAddr(HttpServletRequest request)
	 {
		return getDestAddr(request.getSession()); 		 
	 }
	 static public String getUserId(HttpServletRequest request)
	 {
		 try
		 {
			 return getUserData(request).getId();
		 }
		 catch(Exception exp)
		 {
			 
		 }
		 
		 return null;
	 }
	 
}
