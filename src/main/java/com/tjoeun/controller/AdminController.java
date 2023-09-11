package com.tjoeun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tjoeun.dao.UsersList;
import com.tjoeun.shoppingmall.service.AdminService;
import com.tjoeun.shoppingmall.vo.UsersVO;


@Controller

public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private SqlSession sqlSession;
	
	@RequestMapping("/adminPage")
	public String adminPage(HttpServletRequest request, Model model) {
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) { }
		AdminService service = AdminService.getInstance();
		int totalCount = service.selectCount();
		UsersList usersList = new UsersList();
		usersList.initusersList(pageSize, totalCount, currentPage);
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		hmap.put("startNo", usersList.getStartNo());
		hmap.put("endNo", usersList.getEndNo());
		usersList.setList(service.selectList(hmap));
		model.addAttribute("usersList", usersList);
		return "adminPage";
	}
	
	@RequestMapping("/userView")
	public String contentView(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 contentView() 메소드 실행");
		AdminService service = AdminService.getInstance();
		String id = request.getParameter("id");
		UsersVO usersVO = service.selectById(id);
		model.addAttribute("vo", usersVO);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		model.addAttribute("enter", "\r\n");
		return "userView";
	}
	
	@RequestMapping("/deleteId")
	public String delete(HttpServletRequest request, Model model) {
		AdminService service = AdminService.getInstance();
		String id = request.getParameter("id");
		logger.info("asd");
		service.deleteId(id);
		logger.info("qwe");
		model.addAttribute("currentPage", request.getParameter("currentPage"));
	    return "redirect:adminPage";
	}
	@RequestMapping("/restoreId")
	public String restore(HttpServletRequest request, Model model) {
		AdminService service = AdminService.getInstance();
		String id = request.getParameter("id");
		logger.info("asd");
		service.restoreId(id);
		logger.info("qwe");
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
	@RequestMapping(value = "/updateId", method = RequestMethod.POST)
	public String update(HttpServletRequest request, Model model, UsersVO usersVO) {
		logger.info("컨트롤러의 update() 메소드 실행 - 커맨드 객체 사용");
		AdminService service = AdminService.getInstance();
		System.out.println("컨트롤러의 usersVO(): " + usersVO);
		service.updateId(usersVO);
		System.out.println("testtest");
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
}
