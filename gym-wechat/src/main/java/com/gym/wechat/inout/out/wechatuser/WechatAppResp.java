package com.gym.wechat.inout.out.wechatuser; 

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月22日 下午4:21:20 
 * 类说明 
 */
public class WechatAppResp implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6284232888193143917L;

	private long timestamp;
	
	private String appid;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	
	
}
 