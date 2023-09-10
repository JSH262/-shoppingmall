package com.tjoeun.shoppingmall.config.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.vo.UsersVO;


public class LoginChkUrl 
{
	static private Logger log = LoggerFactory.getLogger(LoginChkUrl.class);
	
	final static private List<LoginChkUrlData> permissions = Arrays.asList(
		new LoginChkUrlData("/chatting", null, new String[] {UsersType.BUYER, UsersType.SELLER}, "/login"),
		new LoginChkUrlData("/login", null, null, null),
		new LoginChkUrlData("/UserLogin", null, null, null),
		new LoginChkUrlData("/CompanyInsert", null, null, null),
		new LoginChkUrlData("/product/payment/list", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/product/payment", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/product/order", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/product/modify", null, new String[] {UsersType.SELLER}, "/error/login"),
		new LoginChkUrlData("/product/list", null, null, null),
		new LoginChkUrlData("/product/insert", null, new String[] {UsersType.SELLER}, "/login"),
		new LoginChkUrlData("/product/detail", null, null, null),
		new LoginChkUrlData("/product/breakdown/modify", null, new String[] {UsersType.SELLER, UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/product/breakdown/list", null, new String[] {UsersType.SELLER}, "/error/login"),
		new LoginChkUrlData("/logout", null, null, null),
		new LoginChkUrlData("/destaddr/selected", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/destaddr/remove", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/destaddr/list", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/destaddr/insert", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/cart/list", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/cart/insert", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/cart/updateAmount", null, new String[] {UsersType.BUYER}, "/error/login"),
		new LoginChkUrlData("/cart/deleteProduct", null, new String[] {UsersType.BUYER}, "/error/login"),
		
		new LoginChkUrlData("/join", null, null, null),
		new LoginChkUrlData("/joinType", null, null, null),
		new LoginChkUrlData("/companyInsert", null, null, null),
		new LoginChkUrlData("/UserJoin", null, null, null),
		new LoginChkUrlData("/userIdCheck", null, null, null),
		new LoginChkUrlData("/userIdCheck", null, null, null),
		new LoginChkUrlData("/myPage", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/reviewList.do", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/ReviewInsert", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/review", null, new String[] {UsersType.BUYER}, "/login"),
		new LoginChkUrlData("/deleteReview", null, new String[] {UsersType.BUYER}, "/login"),
		
		new LoginChkUrlData("/image", "GET", null, null),
		new LoginChkUrlData("/image", "POST", new String[] {UsersType.SELLER}, null)//POST만 검사
	);
	
	static private class LoginChkUrlData
	{
		private String uri;
		private String method;
		private String[] userType;
		private String redirectUrl;
			
		private LoginChkUrlData(String uri, String method, String[] userType, String redirectUrl)
		{
			this.uri = uri;
			this.method = method;
			this.userType = userType;
			this.redirectUrl = redirectUrl;
		}

		public String getUri() {
			return uri;
		}

		public String getMethod() {
			return method;
		}

		public String[] getUserType() {
			return userType;
		}
		
		public String getRedirectUrl() {
			return redirectUrl;
		}

		@Override
		public String toString() {
			return "LoginChkUrlData [uri=" + uri + ", method=" + method + ", userType=" + Arrays.toString(userType)
					+ ", redirectUrl=" + redirectUrl + "]";
		}
		
		
	}
	
	static public String getUri(HttpServletRequest request)
	{
		String contextPath = request.getContextPath();			
		String uri = request.getRequestURI();
		String value = uri.replace(contextPath, "");
		
		return value;
	}
	
	static public LoginChkData isCheck(HttpServletRequest request)
	{
		String uri = getUri(request);
		String method = request.getMethod();
		UsersVO userInfo = AttributeName.getUserData(request);
		String userType = userInfo != null ? userInfo.getType() : null;
		
		for(int i = 0; i<permissions.size(); i++)
		{
			LoginChkUrlData uriData = permissions.get(i);
			int findUriIndex = uri.indexOf(uriData.getUri());
			String uriMethod = uriData.getMethod();
					
			
			if(findUriIndex != -1 && uriMethod == null)
			{
				if(uriData.getUserType() == null)
				{
					//return true;
					return new LoginChkData(null, true);
				}
				else
				{
					if(userType == null)
						//return false;
						return new LoginChkData(uriData.getRedirectUrl(), false);
					
					for(String uriUserType : uriData.getUserType())
					{
						if(uriUserType.equals(userType)) 
						{
							//return true;
							return new LoginChkData(null, true);
						}
					}
				}
			}
			else if(findUriIndex != -1 && uriMethod != null)
			{
				if(uriMethod.equals(method))
				{
					if(uriData.getUserType() == null)
					{
						return new LoginChkData(null, true);
					}
					else
					{
						if(userType == null)
							return new LoginChkData(uriData.getRedirectUrl(), false);
						
						for(String uriUserType : uriData.getUserType())
						{
							if(uriUserType.equals(userType)) 
							{
								return new LoginChkData(null, true);
							}
						}
					}		
				}
			}
		}
		
		
		return new LoginChkData("/index", false);
	}
}
