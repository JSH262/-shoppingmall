package com.tjoeun.shoppingmall.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.DestinationAddressService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/destaddr/insert")
public class DestinationAddressInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
    public DestinationAddressInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		DestinationAddressService service = DestinationAddressService.getInstance(); 
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
					if(service.insert(params) == 1)
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
			exp.printStackTrace();
			retval.put("code", -999);
			retval.put("msg", exp.getMessage());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}

}
