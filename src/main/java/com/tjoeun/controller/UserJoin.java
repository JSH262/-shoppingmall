package com.tjoeun.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.service.UserService;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class UserJoin {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
    public UserJoin() {
        super();
    }
	
    @RequestMapping(value="/join", method=RequestMethod.GET)
    public String join()
    {
    	return "join";
    }

    @RequestMapping(value="/joinType", method=RequestMethod.GET)
    public String joinType()
    {
    	return "joinType";
    }
    

    @RequestMapping(value="/companyInsert", method=RequestMethod.GET)
    public String companyInsert()
    {
    	return "companyInsert";
    }
    
    
    @RequestMapping(value="/UserJoin", method=RequestMethod.POST)
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String type = request.getParameter("type");
		String id = request.getParameter("id").trim();
		String password1 = request.getParameter("password1").trim();
		String password2 = request.getParameter("password2").trim();
		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		String phone = request.getParameter("phone").trim();
		String companyId = request.getParameter("companyId");
		
		

		System.out.println("type: " + type);
		System.out.println("id: " + id);
		System.out.println("password1: " + password1);
		System.out.println("password2: " + password2);
		System.out.println("name: " + name);
		System.out.println("email: " + email);
		System.out.println("phone: " + phone);
		System.out.println("companyId: " + companyId);
		
		// �엯�젰 泥댄겕
		if (id == null || id.equals("") ||  
				password1 == null || password1.equals("") ||
				password2 == null || password2.equals("") ||
				name == null || name.equals("") ||
				email == null || email.equals("") ||
				phone == null || phone.equals("")) {
			response.getWriter().write("1");
			return;
		}

		if (!isValidid(id)) {
			response.getWriter().write("2");
			
			return;
		}
		
		// 鍮꾨�踰덊샇 泥댄겕
		if (!password1.equals(password2)) {
			response.getWriter().write("3"); // �몼
			
			return; // �엯�젰 �뜲�씠�꽣�뿉 �삤瑜섍� �엳�쑝誘�濡� �꽌釉붾┸�쓣 醫낅즺�븳�떎.
		}
		
		if (!isValidpassword(password1)) {
			response.getWriter().write("4");
			
			return;
		}
		
		if (!isValidname(name)) {
			response.getWriter().write("5");
			
			return;
		}
		
		if (!isValidEmailFormat(email)) {
			response.getWriter().write("6");
			
			return;
		}
		
		if (!isValidPhoneNumber(phone)) {
			response.getWriter().write("7");
			
			return;
		}
		
		UsersVO vo = new UsersVO(id, password1, email, phone, type, name, companyId);
		System.out.println(vo);
		
		
		// �뀒�씠釉붿뿉 �쉶�썝 �젙蹂대�� ���옣�븯�뒗 硫붿냼�뱶瑜� �떎�뻾�븳�떎.
		int result = service.UserInsert(vo);
		
		// ���옣 泥댄겕
		if (result == 1) {
			response.getWriter().write("8"); // �몼
		} else {
			System.out.println(result);
			response.getWriter().write("9"); // �몼
		}
	}

	// �빖�뱶�룿 踰덊샇媛� �쑀�슚�븳吏� �솗�씤�븯�뒗 硫붿냼�뱶
	private boolean isValidPhoneNumber(String phoneNumber) {
	    String phoneNumberRegex = "^[0-9]{1,11}$";
	    return phoneNumber.matches(phoneNumberRegex);
	}
	
	// 鍮꾨�踰덊샇媛� �쑀�슚�븳吏� �솗�씤�븯�뒗 硫붿냼�뱶 (��臾몄옄 �냼臾몄옄 �듅�닔臾몄옄 �닽�옄 媛곴컖 1媛� �씠�긽 �룷�븿)
	private boolean isValidpassword(String password) {
	    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_])[a-zA-Z\\d!@#$%^&*_]{8,30}$";
	    return password.matches(passwordRegex);
	}

	// �씠硫붿씪 �삎�떇�씠 �쑀�슚�븳吏� �솗�씤�븯�뒗 硫붿냼�뱶
	private boolean isValidEmailFormat(String email) {
	    String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
	    return email.matches(emailRegex);
	}

	private boolean isValidname(String name) {
	    String regex = ".*\\d.*";
	    return !name.matches(regex);
	}

	// �븘�씠�뵒媛� �쑀�슚�븳吏� �솗�씤�븯�뒗 硫붿냼�뱶 (�뼵�뜑諛� �룷�븿)
	private boolean isValidid(String id) {
	    String idRegex = "^[a-zA-Z0-9_]{4,20}$";
	    return id.matches(idRegex);
	}

}