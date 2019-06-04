package com.gym.wechat.inout.out;

import java.io.Serializable;

public class ComponentAccessTokenVo implements Serializable{

	private static final long serialVersionUID = -4152502365903600529L;

	String componentAccessToken ;

	public String getComponentAccessToken() {
		return componentAccessToken;
	}

	public void setComponentAccessToken(String componentAccessToken) {
		this.componentAccessToken = componentAccessToken;
	}
	
}
