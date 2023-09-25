package com.tjoeun.exception;

public class ErrorCodeException extends Throwable 
{
	private int code = -1;

	public ErrorCodeException(String msg)
	{
		super(msg);
	}
	public ErrorCodeException(int code, String msg)
	{
		super(msg);
		this.code = code;
	}
	
	public int getCode()
	{
		return this.code;
	}
}
