package com.tjoeun.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailController 
{
	
	
	@RequestMapping(value="/product/detail")
	public String productDetail()
	{
		return "product/detail";
	}
}
