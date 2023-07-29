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
import com.tjoeun.helper.Util;
import com.tjoeun.shoppingmall.service.DestinationAddressService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;
import com.tjoeun.shoppingmall.vo.SettingVO;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/destaddr/remove")
public class DestinationAddressRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @Override
	public void init() throws ServletException {

		super.init();		
		this.setting = SettingService.getInstance().select();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationAddressRemoveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		DestinationAddressService service = DestinationAddressService.getInstance(); 
		
		
		try 
		{
			DestinationAddressVO params = new Gson().fromJson(Util.toBody(request), DestinationAddressVO.class);
			if(service.delete(params) == 1)
			{
				retval.put("code", 0);
			}
			else
			{
				retval.put("code", -998);
				retval.put("msg", "error");
			}
		}
		catch (Exception exp) 
		{
			exp.printStackTrace();
			retval.put("code", -999);
			retval.put("msg", exp.getMessage());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
		
	}

}
