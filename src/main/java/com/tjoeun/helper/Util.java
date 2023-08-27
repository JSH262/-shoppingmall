package com.tjoeun.helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.core5.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;

public class Util 
{	
	static Logger logger = LoggerFactory.getLogger(Util.class);
	
	//https://developer111.tistory.com/83
	public static byte[] UUIDtoBytes()
	{
		java.util.UUID uuid = Generators.timeBasedGenerator().generate();
		String[] uuidArr = uuid.toString().split("-");
		StringBuffer sb = new StringBuffer();
		
		sb.append(uuidArr[2]);
		sb.append(uuidArr[1]);
		sb.append(uuidArr[0]);
		sb.append(uuidArr[3]);
		sb.append(uuidArr[4]);
		
		return sb.toString().getBytes();
	}
	public static String UUIDtoString()
	{
		java.util.UUID uuid = Generators.timeBasedGenerator().generate();
		String[] uuidArr = uuid.toString().split("-");
		
		return uuidArr[2]+uuidArr[1]+uuidArr[0]+uuidArr[3]+uuidArr[4];
	}

	
	static public String toString(String item, String defaultValue)
	{
		try
		{
			return item.trim();
		}
		catch(Exception exp)
		{
			
		}
		
		return defaultValue;
	}
	
	/**
	 * 문자를 정수로 바꿔주는 함수
	 * @param item	정수로 바꿀 문자
	 * @param defaultValue 문자가 숫자가 아닐 경우 사용할 값
	 * @return
	 */
	static public Integer toInt(String item, Integer defaultValue)
	{
		try
		{
			return Integer.parseInt(item.trim());
		}
		catch(Exception exp)
		{
			
		}
		
		return defaultValue;
	}
	static public Long toLong(String item, Long defaultValue)
	{
		try
		{
			return Long.parseLong(item.trim());
		}
		catch(Exception exp)
		{
			
		}
		
		return defaultValue;
	}
	
	/**
	 * 문자를 정수로 바꿔주지만 양수만 허용하는 함수
	 * @param item 정수로 바꿀 문자
	 * @param defaultValue 문자가 숫자가 아니거나 음수일 경우 사용할 값
	 * @return 양수
	 */
	static public Integer toPosNum(String item, Integer defaultValue)
	{
		return Math.max(toInt(item, defaultValue), defaultValue);
	}
	
	static public boolean isEmpty(Object item)
	{
		if(item != null)
		{
			if(item instanceof String)
			{
				String tmp = (String)item;
				if(tmp.length() > 0)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			
			return false;
		}
				
		return true;
	}
	
	
    /**
     * 
     * 서버에게 여러개의 파일을 보내는 함수
     * 
     * @param file 서버에게 보낼 파일들
     * @param name 서버에게 보낼 파일 속성의 이름으로 name 뒤에 1부터 시작하여 추가되며 File이 하나면 숫자를 붙이지 않는다.
     * @param url 서버의 URL
     * @param params 서버에게 보낼 문자열 데이터. null일 경우 무시한다.
     * @return 서버가 보낸 데이터
     * @throws IOException
     */
	static public String SendPostImage(MultipartFile[] files, String name, String url, HashMap<String, String> params) throws IOException
    {
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.LEGACY);
    	
    	if(files != null && files.length > 0)
    	{

        	for(int i = 0; i<files.length; i++)
        	{
        		ContentType imageType = ContentType.DEFAULT_BINARY;
            	            
            	switch(files[i].getContentType())
            	{
            	case "image/bmp":
            		imageType = ContentType.IMAGE_BMP;
            		break;
            	
            	case "image/gif":
            		imageType = ContentType.IMAGE_GIF;
            		break;
            		    		
            	case "image/jpg":
            	case "image/jpeg":
            		imageType = ContentType.IMAGE_JPEG;
            		break;
            		
            	case "image/png":
            		imageType = ContentType.IMAGE_PNG;
            		break;
            		
            	case "image/svg":
            		imageType = ContentType.IMAGE_SVG;
            		break;
            		
            	case "image/tiff":
            		imageType = ContentType.IMAGE_TIFF;
            		break;
            		
            	case "image/webp":
            		imageType = ContentType.IMAGE_WEBP;
            		break;
            	}
        		
            	String tmpName = null;
            	if(files.length == 1)
            	{
            		tmpName = name;
            	}
            	else
            	{
            		tmpName = name + (i + 1);
            	}
            	
            	//builder.addPart(tmpName, new FileBody(files[i], imageType));
            	builder.addBinaryBody(tmpName, files[i].getInputStream(), imageType, tmpName);
        	}
        	    	
        	if(params != null)
        	{
        		Set<String> keys = params.keySet();
        		for(String key: keys)
        		{
        			builder.addTextBody(key, params.get(key));	
        		}	
        	}
        	
        	String body = Request.post(url)
        			.body(builder.build())
        			.execute().returnContent().asString();
        	
        	return body;
    	}
    	
    	return null;
    }
	
	
	static public void RecvImage(String fileId, HttpServletRequest request, HttpServletResponse resp, String url) throws Exception
	{
		MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.LEGACY);
		char urlEndWord = url.charAt(url.length() - 1);
		char sepWord = url.indexOf('/') >= 0 ? '/' : '\\';
		
		if(urlEndWord != '/' && urlEndWord != '\\')
		{
			url += sepWord;
		}
		
		url += "image/" + fileId;
				
		
		Response serResp = Request.get(url)
				.body(builder.build())
				.execute();//.returnContent().asBytes();
				
		Content content = serResp.returnContent();
		InputStream is = content.asStream();
		ServletOutputStream sos = resp.getOutputStream();	
		byte[] buffer = new byte[512];
				
		while(true)
		{
			int read = is.read(buffer, 0, buffer.length);
			if(read == -1)
				break;
			
			sos.write(buffer, 0, read);
		}

		resp.setContentType("image/*");
		is.close();
		sos.flush();
		sos.close();
		
	}
	
	static public void printStackTrace(Logger log, Exception exp)
	{
		for(StackTraceElement element : exp.getStackTrace())
		{
			log.error(element.toString());	
		}	
	}
	
	static public JSONObject toJSONObject(HttpServletRequest request) throws IOException
	{
		try
		{
			InputStream is = request.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			JSONParser parser = new JSONParser();
			
			while(true)
			{
				int read = is.read(buffer, 0, buffer.length);
				if(read == -1)
					break;
				
				baos.write(buffer, 0, read);
			}
			
			is.close();
			baos.flush();	
			baos.close();
			
			return 	(JSONObject) parser.parse(new String(baos.toByteArray(), "UTF-8"));
		}
		catch(ParseException exp)
		{
			return null;
		}		
	}

	public static String toBody(HttpServletRequest request) throws IOException, ParseException
	{
		InputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		
		while(true)
		{
			int read = is.read(buffer, 0, buffer.length);
			if(read == -1)
				break;
			
			baos.write(buffer, 0, read);
		}
		
		is.close();
		baos.flush();	
		baos.close();
		
		return 	new String(baos.toByteArray(), "UTF-8");
	}
	
}