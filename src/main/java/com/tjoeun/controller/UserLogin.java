package com.tjoeun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.service.UserService;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class UserLogin {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();

	public UserLogin() {
		super();
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("/UserLogin")
	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String password1 = request.getParameter("password");
		System.out.println(id);
		System.out.println(password1);

		if (id == null || id.equals("") || password1 == null || password1.equals("")) {
			response.getWriter().write("1");
			return;
		}

		UsersVO vo = new UsersVO(id, password1);
		
		int res = service.userLogin(vo);
		// 1 넘어오면 id에 해당하는 password가 있음
		if (res == 1) {
			// 로그인 성공
			UsersVO svo = service.selectVO(id);
			AttributeName.setUserData(request, svo);
			response.getWriter().write("0"); 
		} else {
			// 로그인 실패
			response.getWriter().write("1");
		}

	}

}