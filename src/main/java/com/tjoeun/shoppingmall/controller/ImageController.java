package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.SettingVO;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class ImageController {
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
    
    @PostConstruct
	public void init() throws ServletException {
		this.setting = SettingService.getInstance().select();
    }

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String id) 
	{
		try 
		{
			com.tjoeun.helper.Util.RecvImage(id, request, response, this.setting.getUploadPath());
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	protected void doPost(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String url = setting.getUploadPath();
		
		try 
		{
			String strJson = com.tjoeun.helper.Util.SendPostImage(new MultipartFile[] {request.getFile("file")}, "file", url, null);

			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(strJson);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			JSONObject jObj = new JSONObject();
			
			jObj.put("code", -99);
			jObj.put("msg", e.getMessage());
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jObj.toString());
		}
	}

}