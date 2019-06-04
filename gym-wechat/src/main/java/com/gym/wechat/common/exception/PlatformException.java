package com.gym.wechat.common.exception;

import com.gym.common.exception.IExceptionType;

public class PlatformException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -883790721507851239L;

	private int code;
	
	private String msg;

	public PlatformException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public PlatformException(IExceptionType iExceptionType){
		super(iExceptionType.getMsg());
		this.code=iExceptionType.getCode();
		this.msg=iExceptionType.getMsg();
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
	
	

}
