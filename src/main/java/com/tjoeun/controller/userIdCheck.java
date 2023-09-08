package com.tjoeun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.service.UserService;

@Controller
public class userIdCheck {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
    public userIdCheck() {
        super();
    }

	@RequestMapping("/userIdCheck")
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegisterCheck �꽌釉붾┸�쓽 actionDo() 硫붿냼�뱶 �떎�뻾");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id").trim();
		System.out.println(id);
		
		// �쉶�썝 媛��엯�븯�젮�뒗 �븘�씠�뵒媛� �뀒�씠釉붿뿉 議댁옱�븯�뒗媛� �뙋�떒�븯�뒗 硫붿냼�뱶瑜� �떎�뻾�븳�떎.
		int result = service.IDCheck(id);
		if (id.trim().equals("")) {
			result = 3;
		}
		response.getWriter().write(result + "");
	}

}