package com.tjoeun.shoppingmall.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tjoeun.shoppingmall.service.SettingService;
import com.tjoeun.shoppingmall.vo.SettingVO;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/image/*")
public class ImageController extends HttpServlet {
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
    public ImageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			com.tjoeun.helper.Util.RecvImage(request, response, this.setting.getUploadPath());
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		MultipartRequest mr = new MultipartRequest(request, this.getServletContext().getRealPath(""), 10 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		String url = setting.getUploadPath();
		String strJson = com.tjoeun.helper.Util.SendPostImage(new File[] {mr.getFile("file")}, "file", url, null);

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(strJson);
		
	}

}
