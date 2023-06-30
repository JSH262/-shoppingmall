package com.tjoeun.shoppingmall.service;

public class ProductService {
	static ProductService g_inst = new ProductService();
	ProductService() {}
	
	static public ProductService getInstance()
	{
		return g_inst;
	}
	
	
	
}
