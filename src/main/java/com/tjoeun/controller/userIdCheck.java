package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.service.UserService;

@WebServlet("/userIdCheck")
public class userIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
    public userIdCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegisterCheck 서블릿의 doGet() 메소드 실행");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegisterCheck 서블릿의 doPost() 메소드 실행");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegisterCheck 서블릿의 actionDo() 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userId = request.getParameter("userId").trim();
		System.out.println(userId);
		
		// 회원 가입하려는 아이디가 테이블에 존재하는가 판단하는 메소드를 실행한다.
		int result = service.IDCheck(userId);
		if (userId.trim().equals("")) {
			result = 3;
		}
		response.getWriter().write(result + "");
	}

}






