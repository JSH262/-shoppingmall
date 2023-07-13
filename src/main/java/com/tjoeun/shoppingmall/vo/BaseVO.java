package com.tjoeun.shoppingmall.vo;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

public class BaseVO 
{
	Integer currentPage;
	Integer pageSize;
	String choose;
	Integer endNo;
	Integer startNo;
	Integer rnum;
	
	public void init(HttpServletRequest request) throws Exception
	{
		 Method[] childMethods = this.getClass().getMethods();
	   	 for(Method item: childMethods)
	   	 {
	   		 switch(item.getName().substring(0, 3))
	   		 {
	   		 case "set":
	   			 String original = item.getName().substring(3, item.getName().length());
	   			 StringBuffer sb = new StringBuffer(original.length());
	   							 
	   			 sb.append(Character.toLowerCase(original.charAt(0)));
	   			 sb.append(original.substring(1));
	   			 
	   			 String name = sb.toString();
	   			 String param = request.getParameter(name);   		 
	   			 Object setterValue = null;
	   			 
	   			 
	   			 String typeName = item.getParameters()[0].getType().getName();
	   			 switch(typeName)
	   			 {
	   			 case "java.lang.String":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = param;
	   					 }
	   				 }
	   				 
	   				 break;
	   				 
	   			 case "int":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Integer.parseInt(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Integer":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Integer.parseInt(param.trim());
	   					 }
	   				 }
	   				 break;
	   				 
	   			 case "long":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Long.parseLong(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0L;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0L;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Long":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Long.parseLong(param.trim());
	   					 }
	   				 }
	   				 break;

	   			 case "float":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Float.parseFloat(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0.0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0.0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Float":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Float.parseFloat(param.trim());
	   					 }
	   				 }
	   				 break;

	   			 case "double":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Double.parseDouble(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0.0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0.0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Double":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Double.parseDouble(param.trim());
	   					 }
	   				 }
	   				 break;
	   				 
	   			 default:
	   				 throw new Exception(typeName + "은 지원하지 않습니다.");
	   			 }
	   			 
	   			 item.invoke(this, setterValue);
	   		 }
	   	 }
	}
	
	public void init(MultipartRequest request) throws Exception
	{
		 Method[] childMethods = this.getClass().getMethods();
	   	 for(Method item: childMethods)
	   	 {
	   		 switch(item.getName().substring(0, 3))
	   		 {
	   		 case "set":
	   			 String original = item.getName().substring(3, item.getName().length());
	   			 StringBuffer sb = new StringBuffer(original.length());
	   							 
	   			 sb.append(Character.toLowerCase(original.charAt(0)));
	   			 sb.append(original.substring(1));
	   			 
	   			 String name = sb.toString();
	   			 String param = request.getParameter(name);   		 
	   			 Object setterValue = null;
	   			 
	   			 
	   			 String typeName = item.getParameters()[0].getType().getName();
	   			 switch(typeName)
	   			 {
	   			 case "java.lang.String":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = param;
	   					 }
	   				 }
	   				 
	   				 break;
	   				 
	   			 case "int":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Integer.parseInt(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Integer":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Integer.parseInt(param.trim());
	   					 }
	   				 }
	   				 break;
	   				 
	   			 case "long":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Long.parseLong(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0L;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0L;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Long":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Long.parseLong(param.trim());
	   					 }
	   				 }
	   				 break;

	   			 case "float":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Float.parseFloat(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0.0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0.0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Float":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Float.parseFloat(param.trim());
	   					 }
	   				 }
	   				 break;

	   			 case "double":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Double.parseDouble(param.trim());
	   					 }
	   					 else
	   					 {
	   						 setterValue = 0.0;
	   					 }
	   				 }
	   				 else
	   				 {
	   					 setterValue = 0.0;
	   				 }
	   				 break;
	   				 
	   			 case "java.lang.Double":
	   				 if(param != null)
	   				 {
	   					 param = param.trim();
	   					 if(param.length() > 0)
	   					 {
	   						 setterValue = Double.parseDouble(param.trim());
	   					 }
	   				 }
	   				 break;
	   				 
	   			 default:
	   				 throw new Exception(typeName + "은 지원하지 않습니다.");
	   			 }
	   			 
	   			 item.invoke(this, setterValue);
	   		 }
	   	 }
	}
	

	
	public Integer getRnum() {
		return rnum;
	}

	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}

	public Integer getEndNo() {
		return endNo;
	}

	public void setEndNo(Integer endNo) {
		this.endNo = endNo;
	}

	public Integer getStartNo() {
		return startNo;
	}

	public void setStartNo(Integer startNo) {
		this.startNo = startNo;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
