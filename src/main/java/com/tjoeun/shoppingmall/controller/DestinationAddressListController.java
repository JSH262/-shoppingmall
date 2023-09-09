package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tjoeun.shoppingmall.service.DestinationAddressService;
import com.tjoeun.shoppingmall.vo.DestinationAddressVO;

/**
 * Servlet implementation class ImageController
 */
@Controller
public class DestinationAddressListController{
	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DestinationAddressService destinationAddressService;
	
    @RequestMapping(value="/destaddr/list", method=RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		DestinationAddressService service = destinationAddressService; 
		
		try
		{
			JSONObject result = new JSONObject();
			List<DestinationAddressVO> list = service.selectList(null);
			
			result.put("list", list);
			retval.put("code", 0);
			retval.put("result", result);
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
