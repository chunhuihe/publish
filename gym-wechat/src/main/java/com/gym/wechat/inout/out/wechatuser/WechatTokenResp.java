package com.gym.wechat.inout.out.wechatuser; 

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月17日 上午9:58:07 
 * 类说明 
 */
public class WechatTokenResp implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7685485674009760716L;
	
	
	private String accessToken;


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	
	
	

}
 