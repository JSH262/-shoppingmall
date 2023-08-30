package com.tjoeun.shoppingmall.config.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.vo.UsersVO;

import jdk.internal.org.jline.utils.Log;

public class LoginChkUrl 
{
	static private Logger log = LoggerFactory.getLogger(LoginChkUrl.class);
	
	final static private LoginChkUrlData[] permissions = {
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
	};
	
	static private class LoginChkUrlData
	{
		private String uri;
		private String method;
		private String[] userType;
			
		private LoginChkUrlData(String uri, String method, String[] userType)
		{
			this.uri = uri;
			this.method = method;
			this.userType = userType;
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

		@Override
		public String toString() {
			return "LoginChkUrlData [url=" + uri + ", method=" + method + ", userType=" + Arrays.toString(userType)
					+ "]";
		}
		
		
	}
	
	static public String getUri(HttpServletRequest request)
	{
		String contextPath = request.getContextPath();			
		String uri = request.getRequestURI();
		String value = uri.replace(contextPath, "");
		
		return value;
	}
	
	static public boolean isCheck(HttpServletRequest request)
	{
		String uri = getUri(request);
		String method = request.getMethod();
		UsersVO userInfo = AttributeName.getUserData(request);
		String userType = userInfo != null ? userInfo.getType() : null;
		
		for(int i = 0; i<permissions.length; i++)
		{
			LoginChkUrlData uriData = permissions[i];
			int findUriIndex = uri.indexOf(uriData.getUri());
			String uriMethod = uriData.getMethod();
					
			
			if(findUriIndex != -1 && uriMethod == null)
			{
				if(uriData.getUserType() == null)
				{
					return true;
				}
				else
				{
					if(userType == null)
						return false;
					
					for(String uriUserType : uriData.getUserType())
					{
						if(uriUserType.equals(userType)) 
						{
							return true;
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
						return true;
					}
					else
					{
						if(userType == null)
							return false;
						
						for(String uriUserType : uriData.getUserType())
						{
							if(uriUserType.equals(userType)) 
							{
								return true;
							}
						}
					}		
				}
			}
		}
		
		
		return false;
	}
}
