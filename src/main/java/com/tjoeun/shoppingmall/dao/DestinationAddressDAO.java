package com.tjoeun.shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tjoeun.shoppingmall.vo.DestinationAddressVO;

public class DestinationAddressDAO 
{
	static DestinationAddressDAO g_inst = new DestinationAddressDAO();
	DestinationAddressDAO() {}
	
	static public DestinationAddressDAO getInstance()
	{
		return g_inst;
	}

	public List<DestinationAddressVO> selectList(SqlSession mapper, DestinationAddressVO vo) 
	{
		return mapper.selectList("DestinationAddress.selectList", vo);
	}
	public int insert(SqlSession mapper, DestinationAddressVO vo) 
	{
		return mapper.insert("DestinationAddress.insert", vo);
	}
	
	public DestinationAddressVO select(SqlSession mapper, DestinationAddressVO vo) 
	{
		return (DestinationAddressVO) mapper.selectOne("DestinationAddress.select", vo);
	}
	public int update(SqlSession mapper, DestinationAddressVO vo) 
	{
		return mapper.update("DestinationAddress.update", vo);
	}
	
	public int delete(SqlSession mapper, DestinationAddressVO vo) 
	{
		return mapper.delete("DestinationAddress.delete", vo);
	}
	
	
}

