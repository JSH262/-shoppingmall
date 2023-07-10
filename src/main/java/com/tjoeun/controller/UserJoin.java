package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.service.UserService;
import com.tjoeun.shoppingmall.vo.UsersVO;

@WebServlet("/UserJoin")
public class UserJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = UserService.getInstance();
    public UserJoin() {
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
		
		// 입력 체크
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
		
		// 비밀번호 체크
		if (!password1.equals(password2)) {
			response.getWriter().write("3"); // ②
			
			return; // 입력 데이터에 오류가 있으므로 서블릿을 종료한다.
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
		
		
		// 테이블에 회원 정보를 저장하는 메소드를 실행한다.
		int result = service.UserInsert(vo);
		
		// 저장 체크
		if (result == 1) {
			response.getWriter().write("8"); // ②
		} else {
			System.out.println(result);
			response.getWriter().write("9"); // ②
		}
	}

	// 핸드폰 번호가 유효한지 확인하는 메소드
	private boolean isValidPhoneNumber(String phoneNumber) {
	    String phoneNumberRegex = "^[0-9]{1,11}$";
	    return phoneNumber.matches(phoneNumberRegex);
	}
	
	// 비밀번호가 유효한지 확인하는 메소드 (대문자 소문자 특수문자 숫자 각각 1개 이상 포함)
	private boolean isValidpassword(String password) {
	    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_])[a-zA-Z\\d!@#$%^&*_]{8,30}$";
	    return password.matches(passwordRegex);
	}

	// 이메일 형식이 유효한지 확인하는 메소드
	private boolean isValidEmailFormat(String email) {
	    String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
	    return email.matches(emailRegex);
	}

	private boolean isValidname(String name) {
	    String regex = ".*\\d.*";
	    return !name.matches(regex);
	}

	// 아이디가 유효한지 확인하는 메소드 (언더바 포함)
	private boolean isValidid(String id) {
	    String idRegex = "^[a-zA-Z0-9_]{4,20}$";
	    return id.matches(idRegex);
	}

}










