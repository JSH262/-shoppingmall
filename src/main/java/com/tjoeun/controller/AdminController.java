package com.tjoeun.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dao.UsersList;
import com.tjoeun.shoppingmall.service.AdminService;
import com.tjoeun.shoppingmall.vo.UsersVO;


@Controller

public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private SqlSession sqlSession;
	@RequestMapping("/adminPage")
	public String home(HttpServletRequest request, Model model) {
		logger.info("MvcBoard 게시판 실행");
		return "rediract:adminPage";
	}
	
	@RequestMapping("/adminPage")
	public String adminPage(HttpServletRequest request, Model model) {
		logger.info("컨트롤러의 adminPage() 메소드 실행");
		
		int pageSize = 10;
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) { }
		AdminService service = AdminService.getInstance();
		int totalCount = service.selectCount();
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		UsersList usersList = ctx.getBean("usersList", UsersList.class);
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
		logger.info("컨트롤러의 delete() 메소드 실행");
		AdminService service = AdminService.getInstance();
		String id = request.getParameter("id");
		service.deleteId(id);
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model, UsersVO usersVO) {
		logger.info("컨트롤러의 update() 메소드 실행 - 커맨드 객체 사용");
		
		model.addAttribute("currentPage", request.getParameter("currentPage"));
		return "redirect:adminPage";
	}
	
}
