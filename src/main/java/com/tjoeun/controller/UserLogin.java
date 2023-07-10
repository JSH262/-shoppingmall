package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjoeun.service.UserService;
import com.tjoeun.shoppingmall.vo.UsersVO;

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
			
			//String id = request.getParameter("id").trim();
			String id = request.getParameter("id");
			//String password1 = request.getParameter("password").trim();
			String password1 = request.getParameter("password");
			System.out.println(id);
			System.out.println(password1);
			
			
			// 입력 체크 (입력이 없거나 공백)
			if (id == null || id.equals("") || 
					password1 == null || password1.equals("")) {
				response.getWriter().write("1");
				return;
			}
			
			UsersVO vo = new UsersVO(id, password1);
			int res = service.userLogin(vo);
			if (res == 0) {
				UsersVO svo = service.selectVO(id);
				HttpSession session = request.getSession();
			    	session.setAttribute("user", svo);
			    	// 로그인 성공한 경우 처리할 로직 작성
				response.getWriter().write("0"); // ②
			} else {
				response.getWriter().write("1"); // ②
			}
			
	}

}
