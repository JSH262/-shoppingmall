package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tjoeun.exception.ErrorCodeException;
import com.tjoeun.shoppingmall.service.CartService;
import com.tjoeun.shoppingmall.service.ReviewService;
import com.tjoeun.shoppingmall.vo.CartVO;
import com.tjoeun.shoppingmall.vo.ReviewVO;


@Controller
public class ReviewController {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReviewService reviewService;
	
    @RequestMapping(value="/review", method=RequestMethod.GET)
    public String review(@RequestParam String id, RedirectAttributes redirectAttributes)
    {
    	int orderId = Integer.parseInt(id);
    	if (reviewService.already(orderId) == 1) {
    		redirectAttributes.addFlashAttribute("message", "�씠誘� �옉�꽦�븳 由щ럭媛� �엳�뒿�땲�떎.");
    		return "/product/payment/list";
    	}
    	return "review";
    }
    
    @RequestMapping(value="/ReviewInsert", method=RequestMethod.POST)
   	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
   		response.setContentType("text/html; charset=UTF-8");
   		
   		String userId = request.getParameter("userId");
   		int productId = Integer.parseInt(request.getParameter("productId"));
   		int orderId = Integer.parseInt(request.getParameter("orderId"));
   		float score = Float.parseFloat(request.getParameter("score"));
   		String contents = request.getParameter("contents").trim();
   		

   		System.out.println("userId: " + userId);
   		System.out.println("productId: " + productId);
   		System.out.println("orderId: " + orderId);
   		System.out.println("score: " + score);
   		System.out.println("contents: " + contents);


   		if (contents.length() < 5) {
			response.getWriter().write("2");
			
			return;
		}
   		
   		ReviewVO vo = new ReviewVO(userId, contents, productId, orderId, score);
   		System.out.println(vo);
   		
   		
   		try
   		{
	   		int result = reviewService.ReviewInsert(vo);
	   		
	   		
	   		if (result == 1) {
	   			response.getWriter().write("1"); // 占쎈ぜ
	   		} else {
	   			System.out.println(result);
	   			response.getWriter().write("3"); // 占쎈ぜ
	   		}
   		}
   		catch(ErrorCodeException code)
   		{
   			response.getWriter().write(code.getCode() + ""); // 占쎈ぜ
   		}
   	}
    
    @RequestMapping(value="/reviewList.do", method=RequestMethod.GET)
   	protected String reviewDo(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
   		request.setCharacterEncoding("UTF-8");
   		response.setContentType("text/html; charset=UTF-8");
   		
   		System.out.println("userId: " + userId);

   		List<Object> list = reviewService.selectByUserId(userId);
   		
   		model.addAttribute("list", list);
   		
   		return "reviewList";
   	}
    
	@RequestMapping(value="/deleteReview", method=RequestMethod.POST)
	protected void deletReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));

		ReviewVO vo = new ReviewVO();
		vo.setId(id);
		System.out.println(vo.getId());
		int res = reviewService.deleteReview(vo);
		if (res == 1) {
			response.getWriter().write("0"); // 占쎈ぜ
		} else {
			response.getWriter().write("1"); // 占쎈ぜ
		}
	}

	
}
