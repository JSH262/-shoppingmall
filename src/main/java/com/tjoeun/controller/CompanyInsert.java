package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.service.UserService;
import com.tjoeun.vo.CompanyVO;

@Controller
public class CompanyInsert {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
    public CompanyInsert() {
        super();
    }

	
	@RequestMapping("/CompanyInsert")
	public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("액션두");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String Name = request.getParameter("Name");
		String Id = request.getParameter("Id").trim();
		String Address1 = request.getParameter("Address1").trim();
		String Address2 = request.getParameter("Address2").trim();
		String Stamp = request.getParameter("Stamp").trim();
		
		System.out.println(Name);
		System.out.println(Id);
		System.out.println(Address1);
		System.out.println(Address2);
		System.out.println(Stamp);
		
		// 입력 체크
		if (Name == null || Name.equals("") ||  
				Id == null || Id.equals("") ||
				Address1 == null || Address1.equals("") ||
				Address2 == null || Address2.equals("") ||
				Stamp == null || Stamp.equals("")) {
			response.getWriter().write("3");
			return;
		}
		
		CompanyVO vo = new CompanyVO(Name, Id, Address1, Address2, Stamp);
		
		int result = userService.companyInsert(vo);
		if (result == 1) {
			response.getWriter().write("1"); // ②
		} else {
			System.out.println(result);
			response.getWriter().write("2"); // ②
		}
	}
}