package com.tjoeun.shoppingmall.controller;

import java.io.Console;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.dao.MyPageDAO;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.MyPageService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class myPageController 
{	
	private static final Logger logger = LoggerFactory.getLogger(MyPageDAO.class);
	private static final long serialVersionUID = 1L;
	MyPageService service = MyPageService.getInstance();
	
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	protected String myPageList(HttpServletRequest request)
	{
		return "myPage/list";
	}
	
	@RequestMapping(value="/myPage/passwordCheck", method=RequestMethod.GET)
	protected String myPagePasswordCheck(HttpServletRequest request)
	{
		return "myPage/passwordCheck";
	}
	
	@RequestMapping(value="/myPage/passwordUpdate", method=RequestMethod.GET)
	protected String myPagePasswordUpdate(HttpServletRequest request)
	{
		return "myPage/passwordUpdate";
	}
	
	@RequestMapping(value="/myPage/userUpdate", method=RequestMethod.GET)
	protected String myPageUserUpdate(HttpServletRequest request)
	{
		return "myPage/userUpdate";
	}
	
	@RequestMapping(value="/myPage/unregister", method=RequestMethod.GET)
	protected String myPageUnregister(HttpServletRequest request)
	{
		return "myPage/unregister";
	}
	
	@RequestMapping(value="/myPage/passwordCheckF", method=RequestMethod.POST)
	protected void myPagePasswordCheckF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("userId");
		String password = request.getParameter("password");

		if (password == null || password.equals("")) {
			response.getWriter().write("1");
			return;
		}

		UsersVO vo = new UsersVO(id, password);
		int res = service.passwordCheck(vo);
		if (res == 1) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("2"); // �몼
		}

	}
	
	@RequestMapping(value="/myPage/passwordUpdate", method=RequestMethod.POST)
	protected void myPagePasswordUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		if (password1 == null || password1.equals("") || password2 == null || password2.equals("")) {
			response.getWriter().write("2");
			return;
		}
		
		if (!password1.equals(password2)) {
			response.getWriter().write("1");
			return;
		}
		

		password1 = request.getParameter("password1");
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_])[a-zA-Z\\d!@#$%^&*_]{8,30}$";
		
	    Pattern pattern = Pattern.compile(passwordRegex);
	    Matcher matcher = pattern.matcher(password1);
	
	    if (!matcher.matches()) {
	    	response.getWriter().write("4");
	    }
			
		UsersVO vo = new UsersVO(id, password1);
		System.out.println(vo);
		int res = service.passwordUpdate(vo);
		System.out.println(res);
		if (res == 1) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("3"); // �몼
		}
		
	}
	
	@RequestMapping(value="/myPage/unregister", method=RequestMethod.POST)
	protected void myPageUnregister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		
		UsersVO vo = new UsersVO();
		vo.setId(id);
		int res = service.unregister(vo);
		if (res == 1) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("1"); // �몼
		}
		
	}

	@RequestMapping(value="/myPage/userUpdate", method=RequestMethod.POST)
	protected void myPageUserUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		UsersVO vo = new UsersVO();
		vo.setId(id);
		vo.setName(name);
		vo.setEmail(email);
		vo.setPhone(phone);
		System.out.println(vo);
		int res = service.userUpdate(vo);
		if (res == 1) {
			response.getWriter().write("0"); // �몼
		} else {
			response.getWriter().write("1"); // �몼
		}
		
	}
	
}











