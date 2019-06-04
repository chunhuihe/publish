package com.gym.wechat.inout.in.wechatmessage; 

import com.gym.common.inout.BasePage;

import io.swagger.annotations.ApiModelProperty;


/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午3:42:22 
 * 类说明 
 */
public class JsApiMessageLogRequest extends BasePage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9218677705218963122L;
	@ApiModelProperty("分配的appId")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	
}
 