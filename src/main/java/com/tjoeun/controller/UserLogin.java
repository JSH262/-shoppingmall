package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.service.UserService;
import com.tjoeun.vo.UserVO;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
	 public UserLogin() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			actionDo(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			actionDo(request, response);
		}
		
		protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			//String userId = request.getParameter("userId").trim();
			String userId = request.getParameter("userId");
			//String userPassword1 = request.getParameter("userPassword").trim();
			String userPassword1 = request.getParameter("userPassword");
			System.out.println(userId);
			System.out.println(userPassword1);
			
			
			// 입력 체크 (입력이 없거나 공백)
			if (userId == null || userId.equals("") || 
					userPassword1 == null || userPassword1.equals("")) {
				response.getWriter().write("1");
				return;
			}
			
			
			UserVO vo = new UserVO(userId, userPassword1);
			int res = service.userLogin(vo);
			if (res == 0) {
				response.getWriter().write("0"); // ②
			} else {
				response.getWriter().write("1"); // ②
			}
			
	}

}
