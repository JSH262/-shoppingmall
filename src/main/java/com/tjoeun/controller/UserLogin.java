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

		// 占쎌뿯占쎌젾 筌ｋ똾寃� (占쎌뿯占쎌젾占쎌뵠 占쎈씨椰꾧퀡援� �⑤벉媛�)
		if (id == null || id.equals("") || password1 == null || password1.equals("")) {
			response.getWriter().write("1");
			return;
		}

		UsersVO vo = new UsersVO(id, password1);
		
		/*
		 * if (service.use_yn(vo) == 1) { response.getWriter().write("2"); return; }
		 */
		
		int res = service.userLogin(vo);
		if (res == 0) {

			UsersVO svo = service.selectVO(id);
			AttributeName.setUserData(request, svo);
			// 嚥≪뮄�젃占쎌뵥 占쎄쉐�⑤벏釉� 野껋럩�뒭 筌ｌ꼶�봺占쎈막 嚥≪뮇彛� 占쎌삂占쎄쉐
			response.getWriter().write("0"); // 占쎈ぜ
		} else {
			response.getWriter().write("1"); // 占쎈ぜ
		}

	}

}