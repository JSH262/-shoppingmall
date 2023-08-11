package com.tjoeun.shoppingmall.ws;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.vo.UsersVO;

public class HttpSessionManagement
{
	final static Logger logger = LoggerFactory.getLogger(HttpSessionManagement.class);
	
	private HttpSessionManagement() {}
	
	private static HttpSessionManagement instance = new HttpSessionManagement();
	
	static public HttpSessionManagement getInstance()
	{
		return instance;
	}
	
	
	private Set<HttpSession> clients = Collections.synchronizedSet(new HashSet<HttpSession>());
	
	public UsersVO getUserData(String id)
	{
		UsersVO retval = null;
		
		synchronized (clients) 
		{
			Iterator<HttpSession> iter = clients.iterator();
			while(iter.hasNext())
			{
				try
				{
					HttpSession session = iter.next();
					UsersVO vo = AttributeName.getUserData(session);
					
					if(vo.getId().equals(id))
					{
						retval = vo;
						break;
					}
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
		
		return retval;
	}
	
	public boolean isUser(String id)
	{
		return getUserData(id) != null;
	}
	
	public void sessionCreated(HttpSession se) 
	{
		clients.add(se);
	}
	
	public void sessionDestroyed(HttpSession se) 
	{
		clients.remove(se);
	}
	public void sessionDestroyed(String id)
	{
		synchronized (clients) 
		{
			Iterator<HttpSession> iter = clients.iterator();
			while(iter.hasNext())
			{
				try
				{
					HttpSession session = iter.next();
					UsersVO vo = AttributeName.getUserData(session);
					
					if(vo.getId().equals(id))
					{
						clients.remove(session);
						break;
					}
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
	}
	
	
	public void releases()
	{
		Iterator<HttpSession> iter = clients.iterator();
		while(iter.hasNext())
		{
			HttpSession tmp = iter.next();
			long time = tmp.getLastAccessedTime() + tmp.getMaxInactiveInterval();
			
			if(System.currentTimeMillis() >= time)
			{
				clients.remove(tmp);
			}
		}
	}
	
	/**
	 * 세션이 정상인가?
	 * 
	 * @param se
	 * @return
	 */
	public boolean isSession(HttpSession se)
	{
		long time = se.getLastAccessedTime() + se.getMaxInactiveInterval();
		
		if(System.currentTimeMillis() >= time)
		{
			return false;
		}
		
		return true;
	}
}
