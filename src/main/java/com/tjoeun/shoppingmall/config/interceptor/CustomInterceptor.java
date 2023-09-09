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
		LoginChkData checkData = LoginChkUrl.isCheck(request);
		boolean isRetval = checkData.isCheck();
		if(!isRetval)
		{
			// 로그인이 필요한 서비스에서 사용자가 마지막에 접속한 URL로 오류가 발생곳
			request.setAttribute("rejectLastAccessUrl", LoginChkUrl.getUri(request));
			response.sendRedirect(request.getContextPath() + checkData.getRedirectUrl());
		}
		
		return isRetval;
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
