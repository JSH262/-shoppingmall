package com.tjoeun.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjoeun.dao.UserDAO;
import com.tjoeun.service.UserService;
import com.tjoeun.vo.UserVO;

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
		
		String userType = request.getParameter("userType");
		String userId = request.getParameter("userId").trim();
		String userPassword1 = request.getParameter("userPassword1").trim();
		String userPassword2 = request.getParameter("userPassword2").trim();
		String userName = request.getParameter("userName").trim();
		String userEmail = request.getParameter("userEmail").trim();
		String userPhone = request.getParameter("userPhone").trim();
		String companyId = request.getParameter("companyId").trim();
		

		System.out.println("userType: " + userType);
		System.out.println("userId: " + userId);
		System.out.println("userPassword1: " + userPassword1);
		System.out.println("userPassword2: " + userPassword2);
		System.out.println("userName: " + userName);
		System.out.println("userEmail: " + userEmail);
		System.out.println("userPhone: " + userPhone);
		System.out.println("companyId: " + companyId);
		
		// 입력 체크
		if (userId == null || userId.equals("") ||  
				userPassword1 == null || userPassword1.equals("") ||
				userPassword2 == null || userPassword2.equals("") ||
				userName == null || userName.equals("") ||
				userEmail == null || userEmail.equals("") ||
				userPhone == null || userPhone.equals("")) {
			response.getWriter().write("1");
			return;
		}

		if (!isValiduserId(userId)) {
			response.getWriter().write("2");
			
			return;
		}
		
		// 비밀번호 체크
		if (!userPassword1.equals(userPassword2)) {
			response.getWriter().write("3"); // ②
			
			return; // 입력 데이터에 오류가 있으므로 서블릿을 종료한다.
		}
		
		if (!isValidUserPassword(userPassword1)) {
			response.getWriter().write("4");
			
			return;
		}
		
		if (!isValidUserName(userName)) {
			response.getWriter().write("5");
			
			return;
		}
		
		if (!isValidEmailFormat(userEmail)) {
			response.getWriter().write("6");
			
			return;
		}
		
		if (!isValidPhoneNumber(userPhone)) {
			response.getWriter().write("7");
			
			return;
		}
		
		UserVO vo = new UserVO(userId, userPassword2, userEmail, userPhone, userType, userName, companyId);
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
	private boolean isValidUserPassword(String password) {
	    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_])[a-zA-Z\\d!@#$%^&*_]{8,30}$";
	    return password.matches(passwordRegex);
	}

	// 이메일 형식이 유효한지 확인하는 메소드
	private boolean isValidEmailFormat(String email) {
	    String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
	    return email.matches(emailRegex);
	}

	private boolean isValidUserName(String userName) {
	    String regex = ".*\\d.*";
	    return !userName.matches(regex);
	}

	// 아이디가 유효한지 확인하는 메소드 (언더바 포함)
	private boolean isValiduserId(String userId) {
	    String userIdRegex = "^[a-zA-Z0-9_]{4,20}$";
	    return userId.matches(userIdRegex);
	}

}










