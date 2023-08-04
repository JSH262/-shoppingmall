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

		// String id = request.getParameter("id").trim();
		String id = request.getParameter("id");
		// String password1 = request.getParameter("password").trim();
		String password1 = request.getParameter("password");
		System.out.println(id);
		System.out.println(password1);

		// 입력 체크 (입력이 없거나 공백)
		if (id == null || id.equals("") || password1 == null || password1.equals("")) {
			response.getWriter().write("1");
			return;
		}

		UsersVO vo = new UsersVO(id, password1);
		int res = service.userLogin(vo);
		if (res == 0) {

			UsersVO svo = service.selectVO(id);
			AttributeName.setUserData(request, svo);
			// 로그인 성공한 경우 처리할 로직 작성
			response.getWriter().write("0"); // ②
		} else {
			response.getWriter().write("1"); // ②
		}

	}

}
