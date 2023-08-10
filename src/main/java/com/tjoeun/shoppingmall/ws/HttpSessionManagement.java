package com.tjoeun.shoppingmall.ws;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.shoppingmall.vo.UsersVO;



@WebListener
public class HttpSessionManagement implements javax.servlet.http.HttpSessionListener
{

	private static Set<HttpSession> clients = Collections.synchronizedSet(new HashSet<HttpSession>());
	
	public static UsersVO getUserData(String id)
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
	
	public static boolean isUser(String id)
	{
		return getUserData(id) != null;
	}
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) 
	{
		synchronized(clients)
		{
			clients.add(se.getSession());
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) 
	{
		synchronized (clients) 
		{
			clients.remove(se.getSession());
		}
	}

}
