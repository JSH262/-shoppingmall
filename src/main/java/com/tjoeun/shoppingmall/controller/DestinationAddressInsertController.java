package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.DestinationAddressService;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
import com.tjoeun.shoppingmall.vo.UsersVO;


/**
 * Servlet implementation class ImageController
 */
@Controller
public class DestinationAddressInsertController 
{
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DestinationAddressService destinationAddressService;
	
	
	@RequestMapping(value="/destaddr/insert", method=RequestMethod.POST, consumes="application/json")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		UsersVO user = AttributeName.getUserData(request);
				
		try
		{
			if(user != null)
			{			
				DestinationAddressVO params = new Gson().fromJson(Util.toBody(request), DestinationAddressVO.class);
							
				params.setUserId(user.getId());
				
				
				if(params.getAddr1() == null || params.getAddr2() == null)
				{
					retval.put("code", 1);
					retval.put("msg", "주소를 입력해주세요");
				}
				else if(params.getName() == null)
				{
					retval.put("code", 1);
					retval.put("msg", "받는 분의 이름을 입력해주세요");	
				}
				else if(params.getPhone() == null)
				{
					retval.put("code", 1);
					retval.put("msg", "연락처를 입력해주세요");	
				}
				else if(params.getAddrName() == null)
				{
					retval.put("code", 1);
					retval.put("msg", "배송지 이름을 입력해주세요");	
				}
				else 
				{
					if(this.destinationAddressService.insert(params) == 1)
					{
						retval.put("code", 0);				
					}
					else
					{
						retval.put("code", -998);
						retval.put("msg", "error");				
					}
				}
			}
			else
			{
				retval.put("code", -997);
				retval.put("msg", "error");	
			}
		}
		catch(Exception exp)
		{
			log.error("", exp);
			retval.put("code", -999);
			retval.put("msg", exp.getMessage());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}

}
