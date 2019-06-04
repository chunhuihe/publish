package com.gym.wechat.inout.in.wechatmessage; 


import com.gym.common.inout.BaseRequest;

import io.swagger.annotations.ApiModelProperty;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月17日 上午9:55:02 
 * 类说明 
 */
public class WechatTokenRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4017017429833081817L;
	@ApiModelProperty("appId")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
}
 