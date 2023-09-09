package com.tjoeun.helper;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.mybatis.spring.SqlSessionTemplate;

public class TransactionHelper
{
	private SqlSessionTemplate sqlSession = null;
	private DataSourceTransactionManager transactionManager = null;
	private TransactionStatus status = null;
	
	public TransactionHelper(DataSourceTransactionManager trManager)
	{
		this.transactionManager = trManager;
		this.status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	public TransactionHelper(SqlSessionTemplate sqlSession, DataSourceTransactionManager transactionManager)
	{
		this.sqlSession = sqlSession;
		this.transactionManager = transactionManager;
		this.status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
	}
	
	public <T> T getMapper(Class<T> type)
	{
		if(this.sqlSession != null)
			return this.sqlSession.getMapper(type);
		
		return null;
	}
	
	private void reset()
	{
		this.status = null;
		this.transactionManager = null;
	}
	
	public boolean commit()
	{
		if(this.transactionManager != null && this.status != null)		
		{
			this.transactionManager.commit(this.status);
			this.reset();
			
			return true;
		}
		
		return false;
	}
	
	public boolean rollback()
	{
		if(this.transactionManager != null && this.status != null)		
		{
			this.transactionManager.rollback(this.status);
			this.reset();
			
			return true;
		}
		
		return false;
	}
	
	
}
