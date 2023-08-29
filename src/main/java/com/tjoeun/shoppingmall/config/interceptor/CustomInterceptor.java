package com.tjoeun.shoppingmall.config.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class CustomInterceptor implements HandlerInterceptor 
{
	static final Logger log = LoggerFactory.getLogger(CustomInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		
		String contextPath = request.getContextPath();			
		String uri = request.getRequestURI();
		String value = uri.replace(contextPath, "");		
		UsersVO userInfo = AttributeName.getUserData(request);
			
		log.info("---------------------------");
		if(userInfo != null)
		{
			log.info("로그인 함");
		}
		else
		{
			log.info("로그인 안함");			
			
		}
		

		
		
		log.info(request.getMethod());
		log.info(contextPath);
		log.info(uri);
		log.info(value);
		log.info("---------------------------");
	 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
