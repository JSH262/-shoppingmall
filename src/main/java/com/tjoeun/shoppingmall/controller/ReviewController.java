package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.shoppingmall.service.ReviewService;
import com.tjoeun.shoppingmall.vo.ReviewVO;


@Controller
public class ReviewController {
	private static final long serialVersionUID = 1L;
	private ReviewService service = ReviewService.getInstance();
    public ReviewController() {
        super();
    }
    
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String review()
    {
    	return "review";
    }
    
    @RequestMapping(value="/ReviewInsert", method=RequestMethod.POST)
   	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
   		response.setContentType("text/html; charset=UTF-8");
   		
   		String userId = request.getParameter("userId");
   		int productId = Integer.parseInt(request.getParameter("productId"));
   		float score = Float.parseFloat(request.getParameter("score"));
   		String contents = request.getParameter("contents").trim();
   		

   		System.out.println("userId: " + userId);
   		System.out.println("productId: " + productId);
   		System.out.println("score: " + score);
   		System.out.println("contents: " + contents);

   		
   		ReviewVO vo = new ReviewVO(userId, contents, productId, score);
   		System.out.println(vo);
   		
   		
   		int result = service.ReviewInsert(vo);
   		
   		if (contents.length() < 5) {
			response.getWriter().write("2");
			
			return;
		}
   		
   		if (result == 1) {
   			response.getWriter().write("1"); // ②
   		} else {
   			System.out.println(result);
   			response.getWriter().write("3"); // ②
   		}
   	}
	
}
