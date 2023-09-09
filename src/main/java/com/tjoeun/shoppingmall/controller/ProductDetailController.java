package com.tjoeun.shoppingmall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.CategoryVO;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;

@Controller
public class ProductDetailController 
{
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/product/detail")
	public String productDetail(HttpServletRequest request, Model model)
	{
		UsersVO user = AttributeName.getUserData(request);
		Integer currentPage = 1;
		Integer pageSize = 15;
	
		try
		{
			pageSize = Integer.parseInt(request.getParameter("pageSize"));			
		}
		catch(NumberFormatException exp)
		{
			
		}
		
		try
		{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		catch(NumberFormatException exp)
		{
			
		}
		
		Long id = Long.parseLong(request.getParameter("id"));
		ProductVO params = new ProductVO();
		
		params.setId(id);
		params.setChoose("detail");
		
		ProductVO vo = productService.select(params);
		//String name = vo.getName();
		//String fmtPrice = vo.getFmtPrice();
		//String fmtDiscountPrice = vo.getFmtDiscountPrice();
		//String contents = vo.getContents();
		//String fmtDiscount = vo.getFmtDiscount();
		String thumnail = request.getContextPath();
		//String fmtDeliveryPrice = vo.getFmtDeliveryPrice();
		//String companyName = vo.getCompanyName();
		String delivery = "배송비";
		
		if(vo.getThumbnail() != null)
		{
			thumnail += "/image/" + vo.getThumbnail();
		}
		else				
		{
			thumnail += "/resources/default/noimg.png";
		}
		
		vo.setThumbnail(thumnail);
		
		if(vo.getDeliveryPrice() == 0)
			delivery = "";
		
		
		if(user != null && user.getType().equals(UsersType.SELLER))
		{
			if(vo.getSellerId().equals(user.getId()))
			{
				List<CategoryVO> catList = productService.selectProductCatList(null);
				
				model.addAttribute("catList", catList);			
				model.addAttribute("product", vo);
				model.addAttribute("delivery", delivery);
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("currentPage", currentPage);
			
				return "product/detail/seller";	
			}
			else
			{
				return "redirect:/";
			}
		}
		

		model.addAttribute("product", vo);
		model.addAttribute("delivery", delivery);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		
		
		return "product/detail/buyer";
	}
}
