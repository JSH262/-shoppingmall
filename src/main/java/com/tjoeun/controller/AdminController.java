package com.tjoeun.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.service.AdminService;
import com.tjoeun.shoppingmall.vo.UsersVO;
import com.tjoeun.vo.UsersList;


@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/adminPage")
	public String adminPage(HttpServletRequest request, Model model) {
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) { }
		
		int totalCount = adminService.selectCount();
		UsersList usersList = new UsersList();
		usersList.initusersList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", usersList.getStartNo());
		hmap.put("endNo", usersList.getEndNo());
		usersList.setList(adminService.selectList(hmap));
		model.addAttribute("usersList", usersList);
		return "adminPage";
	}
	
	@RequestMapping("/userView")
	public String contentView(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 contentView() 메소드 실행");
		String id = request.getParameter("id");
		UsersVO usersVO = adminService.selectById(id);
		model.addAttribute("vo", usersVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n");
		return "userView";
	}
	
	@RequestMapping("/deleteId")
	public String delete(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		adminService.deleteId(id);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
	    return "redirect:adminPage";
	}
	@RequestMapping("/restoreId")
	public String restore(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		adminService.restoreId(id);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
	@RequestMapping(value = "/updateId", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Model model, UsersVO usersVO, HttpServletResponse response) throws IOException {
		logger.info("컨트롤러의 update() 메소드 실행 - 커맨드 객체 사용");
		System.out.println("컨트롤러의 usersVO(): " + usersVO);
		String phoneC = "^[0-9]{1,11}$";
		String emailC = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
		if (!usersVO.getEmail().matches(emailC) || !usersVO.getPhone().matches(phoneC)) {
			PrintWriter out = response.getWriter();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			out.println("<script> alert('전화번호 또는 Email양식이 틀렸습니다..');");
			out.println("history.go(-1); </script>"); 
			out.close();
		}
		else {
			adminService.updateId(usersVO);
		}		
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
}