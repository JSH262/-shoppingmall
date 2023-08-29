package com.tjoeun.shoppingmall.config.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class LoginChkUrl 
{
	static private final String[] URL = {
		"/", 
		"/index", 
		"/chatting", 
		"/login", 
		"/UserLogin", 
		"/CompanyInsert", 
		"/product/payment/list", 
		"/product/payment", 
		"/product/order", 
		"/product/modify", 
		"/product/list", 
		"/product/insert", 
		"/product/detail", 
		"/product/breakdown/modify", 
		"/product/breakdown/list", 
		"/logout", 
		"/destaddr/selected", 
		"/destaddr/remove", 
		"/destaddr/list", 
		"/destaddr/insert", 
		"/cart/list", 
		"/cart/insert", 
		"/cart/updateAmount", 
		"/cart/deleteProduct", 
		"/join", 
		"/joinType", 
		"/companyInsert", 
		"/UserJoin", 
		"/userIdCheck",
		"/image"//POST만 검사
	}; 
	
	
	final static private List<LoginChkUrlData> urls = Arrays.asList(
		new LoginChkUrlData("/", null, null), 		// 누구나 이용할 수 있다.
		new LoginChkUrlData("/index", null, null), 	// 누구나 이용할 수 있다.
		new LoginChkUrlData("/chatting", null, new String[] {UsersType.BUYER, UsersType.SELLER}),
		new LoginChkUrlData("/login", null, null),
		new LoginChkUrlData("/UserLogin", null, null),
		new LoginChkUrlData("/CompanyInsert", null, null),
		new LoginChkUrlData("/product/payment/list", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/product/payment", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/product/order", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/product/modify", null, new String[] {UsersType.SELLER}),
		new LoginChkUrlData("/product/list", null, null),
		new LoginChkUrlData("/product/insert", null, new String[] {UsersType.SELLER}),
		new LoginChkUrlData("/product/detail", null, null),
		new LoginChkUrlData("/product/breakdown/modify", null, new String[] {UsersType.SELLER}),
		new LoginChkUrlData("/product/breakdown/list", null, new String[] {UsersType.SELLER}),
		new LoginChkUrlData("/logout", null, null),
		new LoginChkUrlData("/destaddr/selected", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/destaddr/remove", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/destaddr/list", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/destaddr/insert", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/cart/list", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/cart/insert", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/cart/updateAmount", null, new String[] {UsersType.BUYER}),
		new LoginChkUrlData("/cart/deleteProduct", null, new String[] {UsersType.BUYER}),
		
		new LoginChkUrlData("/join", null, null),
		new LoginChkUrlData("/joinType", null, null),
		new LoginChkUrlData("/companyInsert", null, null),
		new LoginChkUrlData("/UserJoin", null, null),
		new LoginChkUrlData("/userIdCheck",null, null),
		
		new LoginChkUrlData("/image", "GET", null),
		new LoginChkUrlData("/image", "POST", new String[] {UsersType.SELLER})//POST만 검사
	);
	
	static private class LoginChkUrlData
	{
		private String url;
		private String method;
		private String[] userType;
			
		private LoginChkUrlData(String url, String method, String[] userType)
		{
			this.url = url;
			this.method = method;
			this.userType = userType;
		}

		public String getUrl() {
			return url;
		}

		public String getMethod() {
			return method;
		}

		public String[] getUserType() {
			return userType;
		}
	}
	
	static public String getUri(HttpServletRequest request)
	{
		String contextPath = request.getContextPath();			
		String uri = request.getRequestURI();
		
		return uri.replace(contextPath, "");
	}
	
	static public boolean isCheck(HttpServletRequest request)
	{
		String uri = getUri(request);
		String method = request.getMethod();
		UsersVO userInfo = AttributeName.getUserData(request);
		String userType = userInfo != null ? userInfo.getType() : null;
		
		for(int i = 0; i<urls.size(); i++)
		{
			LoginChkUrlData data = urls.get(i);
			String tmpMethod = data.getMethod();
			
			// method가 문제라서 이렇게 하면 오류가 발생한다. => 오류는 항상 GET만 검사하고 post는 검사하지 않음
			if(data.getUrl().equals(uri) && tmpMethod == null)
			{
				boolean isUserType = false;
				String[] tmpType = data.getUserType();
			
				if(tmpType != null)
				{
					if(userType != null)
					{
						for(int q = 0; q<tmpType.length; q++)
						{
							if(userType.equals(tmpType[q]))
							{
								isUserType = true;
								break;
							}						
						}	
					}
					else
					{
						isUserType = false;
					}
									
				}
				else
				{
					isUserType = true;
				}
				
				
				return isUserType;
			}
			else if(data.getUrl().equals(uri) && tmpMethod != null)
			{
				if(tmpMethod.equals(method)) 
				{
					String[] tmpType = data.getUserType();
					if(tmpType != null && userType != null)
					{
						boolean isUserType = false;
						for(int q = 0; q<tmpType.length; q++)
						{
							if(userType.equals(tmpType[q]))
							{
								isUserType = true;
								break;
							}
						}
						
						return isUserType;
					}
				}	
			}
		}
		
		
		return false;
	}
}
