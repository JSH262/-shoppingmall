package com.tjoeun.shoppingmall.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

import org.springframework.ui.Model;


/**
 * Servlet implementation class ProductController
 */
@Controller
public class ProductInsertController {
    SettingVO setting = null;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @PostConstruct
	public void init() throws ServletException {
		this.setting = SettingService.getInstance().select();		
	}
    
    @RequestMapping(value="/product/insert", method=RequestMethod.GET)
    public String productInsertView(Model model)
    {
		List<CategoryVO> catList = ProductService.getInstance().selectProductCatList(null);
	
		
		model.addAttribute("catList", catList);
		
		
    	return "product/insert";
    }
    
	@RequestMapping(value="/product/insert", method=RequestMethod.POST)
	public void doPost(MultipartHttpServletRequest request, HttpServletResponse response, ProductVO item) 
	{
		JSONObject retval = new JSONObject();
		
		try
		{
			UsersVO user = AttributeName.getUserData(request);
			if(UsersType.SELLER.equals(user.getType()))
			{
				String sellerId = user.getId();			
				String uploadUrl = setting.createUploadPath("image");
				MultipartFile uploadFile = request.getFile("file");
				
				if(uploadFile != null)
				{
					String uploadResult = com.tjoeun.helper.Util.SendPostImage(new MultipartFile[] {uploadFile}, "file", uploadUrl, null);
					org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
					JSONObject jUploadResult = (JSONObject)parser.parse(uploadResult);
								
					if(jUploadResult.get("code").equals(0) || jUploadResult.get("code").equals(0L))
					{
						// 3. 상품 정보를 저장한다.
						JSONArray arrResult = (JSONArray)jUploadResult.get("result");
						item.setThumbnail((String)arrResult.get(0));		
						retval.put("uploadCode", 0);
					}
					else
					{
						//업로드 실패
						retval.put("uploadCode", jUploadResult.get("code"));
						retval.put("uploadMsg", jUploadResult.get("msg"));
						item.setThumbnail("");
					}
				}
				else
				{
					item.setThumbnail("");
				}
				
				item.setSellerId(sellerId);
							
				if(ProductService.getInstance().insert(item) == 1)
				{
					retval.put("code", 0);
					retval.put("msg", "상품등록 성공");
					retval.put("result", request.getContextPath() + "/product/list");
				}
				else
				{
					retval.put("code", -3);
					retval.put("msg", "상품등록 실패");
				}
			}
			else
			{
				retval.put("code", -99);
				retval.put("msg", "잘못된 접근입니다.");
			}
			
		}
		catch(Exception exp)
		{
			retval.put("code", -2);
			retval.put("msg", exp.getMessage());
			
			exp.printStackTrace();
		}
		
		try
		{
			response.setContentType("applicatoin/json; charset=UTF-8");
			
			response.getWriter().write(retval.toJSONString());
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
}