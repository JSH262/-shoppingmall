package com.tjoeun.shoppingmall.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.SettingVO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/product/insert")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String JSP_PATH = "/WEB-INF/product/";
    SettingVO setting = null;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
    
    
    @Override
	public void init() throws ServletException {

		super.init();
		
		this.setting = SettingService.getInstance().select();		
	}





	public String getCmd(HttpServletRequest request)
    {
    	String uri = request.getRequestURI();
    	int start = uri.lastIndexOf("/");
    	
    	return uri.substring(start + 1);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JSONObject retval = new JSONObject();
		
		
		try
		{
			MultipartRequest mr = new MultipartRequest(request, this.getServletContext().getRealPath(""), 8000000, "UTF-8", new DefaultFileRenamePolicy());
			String uploadUrl = setting.getUploadPath() + "/image/";
			ProductVO item = new ProductVO(mr);

///////////////////////////////////////////////////////////////////////////////////////// 테스트 용
			String sellerId = "asdf1234";
			
			String uploadResult = com.tjoeun.helper.Util.SendPostImage(new File[] {mr.getFile("file")}, "file", uploadUrl, null);
			org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
			JSONObject jUploadResult = (JSONObject)parser.parse(uploadResult);
						
			if(jUploadResult.get("code").equals(0) || jUploadResult.get("code").equals(0L))
			{
				// 3. 상품 정보를 저장한다.
				JSONArray arrResult = (JSONArray)jUploadResult.get("result");
				item.setThumbnail((String)arrResult.get(0));
				item.setSellerId(sellerId);
				
				if(ProductService.getInstance().insert(item) == 1)
				{
					retval.put("code", 0);
					retval.put("msg", "상품등록 성공");
					retval.put("result", request.getContextPath() + "/product/list.jsp");
				}
				else
				{
					retval.put("code", -3);
					retval.put("msg", "상품등록 실패");
				}
			}
			else
			{
				//업로드 실패
				retval.put("code", -1);
				retval.put("msg", jUploadResult.get("msg"));
			}
			
			
		}
		catch(Exception exp)
		{
			retval.put("code", -2);
			retval.put("msg", exp.getMessage());
			
			exp.printStackTrace();
		}
		
		
		response.setContentType("applicatoin/json; charset=UTF-8");
		
		response.getWriter().write(retval.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
