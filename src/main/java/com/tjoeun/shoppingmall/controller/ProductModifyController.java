package com.tjoeun.shoppingmall.controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class ProductModifyController {
	private static final long serialVersionUID = 1L;    
    SettingVO setting = null;
    
    @PostConstruct
  	public void init() throws ServletException {
  		this.setting = SettingService.getInstance().select();		
  	}
    
    
    
    

	@RequestMapping(value="/product/modify", method=RequestMethod.POST)
	protected void doPost(MultipartHttpServletRequest request, HttpServletResponse response, ProductVO params) throws ServletException, IOException 
	{
		UsersVO user = AttributeName.getUserData(request);
		String fileUploadUrl = setting.getUploadPath() + "/image/";
		org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
		JSONObject retval = new JSONObject();
		ProductService service = ProductService.getInstance();
		DecimalFormat numFormat = new DecimalFormat("###,###,###");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) H:m:s");
		String thumbnailUrl = null;
		
		try 
		{
			if(user != null && user.getType().equals(UsersType.SELLER))
			{
				MultipartFile uploadThumImage = request.getFile("file");
				
				if(uploadThumImage != null)
				{
					String strUploadResult = com.tjoeun.helper.Util.SendPostImage(new MultipartFile[] {uploadThumImage}, "file", fileUploadUrl, null);			
					JSONObject jUploadResult = (JSONObject)parser.parse(strUploadResult);
					if(jUploadResult.get("code").equals(0) || jUploadResult.get("code").equals(0L))
					{
						JSONArray result = (JSONArray)jUploadResult.get("result");
						String fileId = (String)result.get(0);
						thumbnailUrl = request.getContextPath() + "/image/" + fileId;
						
						params.setThumbnail(fileId);					
						retval.put("uploadCode", 0);
					}
					else
					{
						//업로드 실패
						retval.put("uploadCode", jUploadResult.get("code"));
						retval.put("uploadMsg", jUploadResult.get("msg"));
					}
				}
				
				params.setSellerId(user.getId());
				
				// 상품 정보를 수정				
				if(service.update(params) == 1)
				{
					JSONObject resultData = new JSONObject();
					double discountPrice = params.getPrice() - (params.getPrice() * (params.getDiscount() / 100.0));
					
					resultData.put("thumbnail", thumbnailUrl);
					resultData.put("fmtPrice", numFormat.format(params.getPrice()) + "원");
					resultData.put("fmtDiscountPrice", numFormat.format(discountPrice) + "원");
					resultData.put("fmtDiscount", params.getDiscount()  + "%");
					resultData.put("fmtAmount", numFormat.format(params.getAmount()) + "개");
					resultData.put("fmtDeliveryPrice", numFormat.format(params.getDeliveryPrice()) + "원");
					retval.put("result", resultData);
					retval.put("code", 0);
				}
				else
				{
					retval.put("code", -9);
					retval.put("msg", "상품정보 수정 실패");	
				}
			}
			else
			{
				retval.put("code", -90);
				retval.put("msg", "상품정보 수정 실패");	
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			retval.put("msg", e.getClass().getName() + ": " + e.getMessage());
			retval.put("code", -6);
		}
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}

	
	
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
				
		MultipartRequest mr = new MultipartRequest(request, this.getServletContext().getRealPath(""), 8000000, "UTF-8", new DefaultFileRenamePolicy());
		String fileUploadUrl = setting.getUploadPath() + "/image/";
		org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
		JSONObject retval = new JSONObject();
		ProductService service = ProductService.getInstance();
		DecimalFormat numFormat = new DecimalFormat("###,###,###");
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) H:m:s");
	
		try 
		{
			ProductVO params = new ProductVO(mr);
			File uploadThumImage = mr.getFile("file");
						
			if(uploadThumImage != null)
			{
				String strUploadResult = com.tjoeun.helper.Util.SendPostImage(new File[] {uploadThumImage}, "file", fileUploadUrl, null);			
				JSONObject jUploadResult = (JSONObject)parser.parse(strUploadResult);
				if(jUploadResult.get("code").equals(0) || jUploadResult.get("code").equals(0L))
				{
					JSONArray result = (JSONArray)jUploadResult.get("result");
					String fileId = (String)result.get(0);
					
					params.setThumbnail(fileId);
					
					// 상품 정보를 수정				
					if(service.update(params) == 1)
					{
						JSONObject resultData = new JSONObject();
						double discountPrice = params.getPrice() - (params.getPrice() * (params.getDiscount() / 100.0));

						
						
						resultData.put("thumbnail", request.getContextPath() + "/image/" + fileId);						
						resultData.put("fmtPrice", numFormat.format(params.getPrice()) + "원");
						resultData.put("fmtDiscountPrice", numFormat.format(discountPrice) + "원");
						resultData.put("fmtDiscount", params.getDiscount()  + "%");
						resultData.put("fmtAmount", numFormat.format(params.getAmount()) + "개");
						resultData.put("fmtDeliveryPrice", numFormat.format(params.getDeliveryPrice()) + "원");
						retval.put("result", resultData);
						retval.put("code", 0);
					}
					else
					{
						retval.put("code", -7);
						retval.put("msg", "상품정보 수정 실패");	
					}
				}
				else
				{
					//업로드 실패
					retval.put("code", -1);
					retval.put("msg", jUploadResult.get("msg"));
				}
			}
			else
			{
				// 상품 정보를 수정				
				if(service.update(params) == 1)
				{
					JSONObject resultData = new JSONObject();
					double discountPrice = params.getPrice() - (params.getPrice() * (params.getDiscount() / 100.0));
					
					resultData.put("thumbnail", null);
					resultData.put("fmtPrice", numFormat.format(params.getPrice()) + "원");
					resultData.put("fmtDiscountPrice", numFormat.format(discountPrice) + "원");
					resultData.put("fmtDiscount", params.getDiscount()  + "%");
					resultData.put("fmtAmount", numFormat.format(params.getAmount()) + "개");
					resultData.put("fmtDeliveryPrice", numFormat.format(params.getDeliveryPrice()) + "원");
					retval.put("result", resultData);
					retval.put("code", 0);
				}
				else
				{
					retval.put("code", -9);
					retval.put("msg", "상품정보 수정 실패");	
				}
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			retval.put("msg", e.getClass().getName() + ": " + e.getMessage());
			retval.put("code", -6);
		}
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		response.getWriter().write(retval.toJSONString());
	}
	*/
}