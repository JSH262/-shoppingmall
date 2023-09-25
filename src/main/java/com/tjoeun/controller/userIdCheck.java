package com.tjoeun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.exception.ErrorCodeException;
import com.tjoeun.service.UserService;

@Controller
public class userIdCheck {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/userIdCheck")
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegisterCheck 서블릿의 actionDo() 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id").trim();
		System.out.println(id);
		
		// 회원 가입하려는 아이디가 테이블에 존재하는가 판단하는 메소드를 실행한다.
		try
		{
			int result = userService.IDCheck(id);
			if (id.trim().equals("")) {
				result = 3;
			}
			response.getWriter().write(result + "");
		}
		catch(ErrorCodeException exp)
		{
			response.getWriter().write(exp.getCode() + "");
		}
	}

}