package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.PaymentVO;

@Mapper
public interface PaymentDAO 
{
	public List<PaymentVO> selectList(PaymentVO vo);
	public int insert(PaymentVO vo);
}


/*
public class PaymentDAO 
{
	static PaymentDAO g_inst = new PaymentDAO();
	PaymentDAO() {}
	
	static public PaymentDAO getInstance()
	{
		return g_inst;
	}

	public List<PaymentVO> selectList(SqlSession mapper, PaymentVO vo) 
	{
		return mapper.selectList("Payment.selectList", vo);
	}
	public int insert(SqlSession mapper, PaymentVO vo) 
	{
		return mapper.insert("Payment.insert", vo);
	}
}


//*/


