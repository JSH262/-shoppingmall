package com.tjoeun.shoppingmall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.*;

import org.apache.hc.core5.annotation.Contract;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tjoeun.helper.AttributeName;
import com.tjoeun.helper.UsersType;
import com.tjoeun.shoppingmall.config.ServerEndpointConfigurator;
import com.tjoeun.shoppingmall.service.ProductService;
import com.tjoeun.shoppingmall.vo.ProductVO;
import com.tjoeun.shoppingmall.vo.UsersVO;


@ServerEndpoint(value="/alert", configurator = ServerEndpointConfigurator.class)
@Controller
public class AlertServer 
{
	JSONParser parser = new JSONParser();
	private static Map<Session, String> clients = Collections.synchronizedMap(new HashMap<Session, String>());
	//private static Map<Session, String> clients = new HashMap<Session, String>();

	
	@Autowired
	ProductService productService;
	
	public static enum RequestCode
	{
		INIT("01", "초기화"),
		PRODUCT_BUY("02", "물건을 구입함"),
		UPDATE("03", "갱신"),
		;
		
		
		
		private RequestCode(String code, String msg)
		{
			this.code = code;
			this.msg = msg;
		}
				
		private String code;
		private String msg;
		
		public String getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
	}
	
	public static enum ResponseCode
	{
		INIT_SUCCESS("03", "초기화 성공"),
		PRODUCT_BUY_ALERT("04", "상품을 구입함"),
		;
		
		private ResponseCode(String code, String msg)
		{
			this.code = code;
			this.msg = msg;
		}
				
		private String code;
		private String msg;
		
		public String getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
		
		
		
	}
	
	
	static public RequestCode StringToRequestCode(String code)
	{
		RequestCode[] codes = RequestCode.values();
		for(int i = 0; i<codes.length; i++)
		{
			if(codes[i].getCode().equals(code))
			{
				return codes[i];
			}
		}
		
		return null;
	}
	
	
	@OnOpen
	public void onOpen(Session session) 
	{
	}

	@OnMessage // 메시지를 받으면 실행
	public void onMessage(String message, Session session) throws IOException 
	{
		
		try 
		{				
			JSONObject jMsg = (JSONObject)parser.parse(message);
			RequestCode code = StringToRequestCode((String)jMsg.get("code"));
					
			
			switch(code)
			{
			case UPDATE:
				{
					String userId = (String)jMsg.get("id");			
					
					HttpSessionManagement.getInstance().update(userId, 0);
				}
				
				break;
			
			case INIT:
				{
					String userId = (String)jMsg.get("id");
					UsersVO userInfo = HttpSessionManagement.getInstance().getUserData(userId);					
					if(userInfo != null)
					{
						if(userInfo.getType().equals(UsersType.SELLER))
						{
							synchronized (clients) 
							{
								clients.put(session, userId);								
							}
						}
						
						int interval = (int)(HttpSessionManagement.getInstance().getMaxInterval(userId) * 0.9);
						JSONObject send = new JSONObject();
						
						send.put("code", ResponseCode.INIT_SUCCESS.getCode());
						send.put("interval", interval);
						
						session.getBasicRemote().sendText(send.toJSONString());
					}
				}
				break;
				
			case PRODUCT_BUY:
				{
					String userId = (String)jMsg.get("id");					
					UsersVO userInfo = HttpSessionManagement.getInstance().getUserData(userId);
										
					if(userInfo != null)
					{
						JSONArray productList = (JSONArray)jMsg.get("productList");
						HashMap<String, ArrayList<Long>> productInfoList = new HashMap<String, ArrayList<Long>>();
						
						
						for(int i = 0; i<productList.size(); i++)
						{
							JSONObject jProduct = (JSONObject)productList.get(i);									
							String sellerId = (String)jProduct.get("sellerId");
							Long productId = (Long)jProduct.get("productId");
							
							ArrayList<Long> tmpProductList = productInfoList.get(sellerId);
							if(tmpProductList != null)
							{
								tmpProductList.add(productId);
							}
							else
							{
								tmpProductList = new ArrayList<Long>();
								
								tmpProductList.add(productId);								
								productInfoList.put(sellerId, tmpProductList);
							}
						}
						
						synchronized (clients) 
						{
							Set<Session> keys = clients.keySet();
							Iterator<Session> iter = keys.iterator();
							while(iter.hasNext())
							{
								Session tmpSession = iter.next();
								String tmpUserId = clients.get(tmpSession);
								Set<String> pKeys = productInfoList.keySet();
								Iterator<String> pIter = pKeys.iterator();
								
								while(pIter.hasNext())
								{
									String sellerId = pIter.next();
									if(tmpUserId.equals(sellerId))
									{
										JSONObject send = new JSONObject();	
										ArrayList<Long> tmpProductIdList = productInfoList.get(sellerId);
										String productNames = "";
										
										for(int i = 0; i<tmpProductIdList.size(); i++)
										{
											ProductVO vo = productService.select(tmpProductIdList.get(i));
											
											
											productNames += ", " + vo.getName();
											
										}
																														
										send.put("code", ResponseCode.PRODUCT_BUY_ALERT.getCode());
										send.put("productNames", productNames.substring(2));
										send.put("link", "/shoppingmall/product/breakdown/list");
										
										tmpSession.getBasicRemote().sendText(send.toJSONString());	
										break;
									}	
								}
								
								
							}
						}
					}
				}
				break;
				
			default:
				break;
			}
			
			
			
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@OnClose // 클라이언트와의 연결이 끊기면 실행
	public void onClose(Session session) 
	{
		synchronized (clients) 
		{
			clients.remove(session);			
		}
	}

	@OnError // 에러 발생 시 실행
	public void onError(Throwable e) 
	{
		e.printStackTrace();
	}
}
