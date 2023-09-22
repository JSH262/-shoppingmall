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
	private HttpSessionManagement() {}
	
	private static HttpSessionManagement instance = new HttpSessionManagement();
	
	static public HttpSessionManagement getInstance()
	{
		return instance;
	}
	
	
	private Set<HttpSession> clients = Collections.synchronizedSet(new HashSet<HttpSession>());
	//private Set<HttpSession> clients = new HashSet<HttpSession>();
	
	public UsersVO getUserData(String id)
	{
		UsersVO retval = null;
		
		synchronized (clients) {
		
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
	
	public int getMaxInterval(String id)
	{
		int retval = 0;
		
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
						retval = session.getMaxInactiveInterval();
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
		synchronized (clients) 
		{
			UsersVO userInfo = AttributeName.getUserData(se);
			if(userInfo != null)
			{

				// 기존에 생성된 유저의 세션 정보를 제거한다.
				Iterator<HttpSession> iter = clients.iterator();
				while(iter.hasNext())
				{
					HttpSession tmpSession = iter.next();
					
					try
					{
						UsersVO tmpUserInfo = AttributeName.getUserData(tmpSession);
						
						if(tmpUserInfo.getId().equals(userInfo.getId()))
						{
								tmpSession.invalidate();
							
							clients.remove(tmpSession);
							break;
						}
					}
					catch(IllegalStateException exp)
					{
						clients.remove(tmpSession);	
					}
				}
				
			}
			
			clients.add(se);	
		}
	}
	
	public void sessionDestroyed(HttpSession se) 
	{
		synchronized (clients) {
			clients.remove(se);	
		}
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
	static final int ADD_INTERVAL = 60 * 60 * 1000;
	public int update(String id, int interval)
	{
		if(interval <= 0)
			interval = ADD_INTERVAL;
		
		int resultInterval = 0;
		synchronized (clients) 
		{
			Iterator<HttpSession> iter = clients.iterator();
			while(iter.hasNext())
			{
				HttpSession tmp = iter.next();				
				UsersVO userInfo = AttributeName.getUserData(tmp);
				if(userInfo.getId().equals(id))
				{
					resultInterval = tmp.getMaxInactiveInterval() + interval;
					
					tmp.setMaxInactiveInterval(resultInterval); 
					break;
				}
			}
		}
		
		return resultInterval;
	}
	
	public void releases()
	{
		synchronized (clients) 
		{
			Iterator<HttpSession> iter = clients.iterator();
			while(iter.hasNext())
			{
				HttpSession tmp = iter.next();
				//tmp.getCreationTime();
				long time = tmp.getLastAccessedTime() + tmp.getMaxInactiveInterval();
				
				if(System.currentTimeMillis() >= time)
				{
					clients.remove(tmp);
				}
			}
		}
	}
	
	/**
	 * 세션이 정상인가?
	 * 
	 * @param se
	 * @return true: 정상, false: 비정상
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
	
	public boolean isSession(String id)
	{
		boolean retval = true;
		synchronized (clients) 
		{			
			Iterator<HttpSession> iter = clients.iterator();
			while(iter.hasNext())
			{
				HttpSession tmp = iter.next();
				UsersVO user = AttributeName.getUserData(tmp);
				
				if(id.equals(user.getId()))
				{
	
					long time = tmp.getLastAccessedTime() + tmp.getMaxInactiveInterval();
					
					if(System.currentTimeMillis() >= time)
					{
						retval = false;
					}
				}
			}
		}
		
		return retval;
	}
}
