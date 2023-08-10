package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.helper.AttributeName;
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
	private static final long serialVersionUID = 1L;
	MyPageService service = MyPageService.getInstance();
	
	@RequestMapping(value="/myPage/list", method=RequestMethod.GET)
	protected String myPageList(HttpServletRequest request, HttpServletResponse response)
	{
		return "myPage/list";
	}
	
	@RequestMapping(value="/myPage/passwordCheck", method=RequestMethod.GET)
	protected String myPagePasswordCheck(HttpServletRequest request, HttpServletResponse response)
	{
		return "myPage/passwordCheck";
	}
	
	
	@RequestMapping("/passwordCheck")
	protected void passwordCheck(HttpServletRequest request, HttpServletResponse response)
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
		if (res == 0) {
			response.getWriter().write("0"); // ②
		} else {
			response.getWriter().write("2"); // ②
		}

	}
	
}
