package com.tjoeun.shoppingmall.vo;

public class SettingVO {
	int id;
	String uploadPath;
	String batchItem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getBatchItem() {
		return batchItem;
	}
	public void setBatchItem(String batchItem) {
		this.batchItem = batchItem;
	}
	
	
	public String createUploadPath(String uri)
	{
		char lastCh = this.uploadPath.charAt(this.uploadPath.length() - 1);
		String retval = this.uploadPath;
		int idx = this.uploadPath.lastIndexOf('/');
		char sep = '/';
		char firstUriCh = uri.charAt(0);
		
		if(idx == -1)
		{
			sep = '\\';
		}
				
		if(lastCh != '\\' && lastCh != '/' && firstUriCh != '\\' && firstUriCh != '/')
		{
			retval = this.uploadPath + sep;	
		}
		
		return retval + uri;
	}
	
	
	
	
}
