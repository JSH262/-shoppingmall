package com.tjoeun.shoppingmall.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.SettingVO;

import jdk.internal.org.jline.utils.Log;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class ImageController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	SettingVO setting = null;
	
	@Autowired
	SettingService settingService;
	
    @PostConstruct
	public void init() throws ServletException {
		this.setting = settingService.select();
    }

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response, 
			@PathVariable String id) 
	{
		try 
		{
			String uploadUrl = this.setting.getUploadPath();
			
			com.tjoeun.helper.Util.RecvImage(id, request, response, uploadUrl);
		}
		catch (Exception e) 
		{
			log.error("", e);
		}
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	protected void doPost(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException 
	{		
		try 
		{
			String uploadUrl = this.setting.getUploadPath();
			char ch = uploadUrl.charAt(uploadUrl.length() - 1);
			if(ch == '/')
			{
			}
			else if(ch == '\\')
			{
			}
			else
			{
				uploadUrl += "/";
			}
			
			uploadUrl += "image";
						
			String strJson = com.tjoeun.helper.Util.SendPostImage(new MultipartFile[] {request.getFile("file")}, "file", uploadUrl, null);

			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(strJson);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			log.error("", e);

			JSONObject jObj = new JSONObject();
			
			jObj.put("code", -99);
			jObj.put("msg", e.getMessage());
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jObj.toString());
		}
	}

}