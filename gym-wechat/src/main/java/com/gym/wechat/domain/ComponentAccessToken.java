package com.gym.wechat.domain;

import java.io.Serializable;

public class ComponentAccessToken implements Serializable{

	private static final long serialVersionUID = 4252619708297316506L;

	private String component_access_token;
	private Integer expires_in;
	public String getComponent_access_token() {
		return component_access_token;
	}
	public void setComponent_access_token(String component_access_token) {
		this.component_access_token = component_access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	@Override
	public String toString() {
		return "ComponentAccessTokenVo [component_access_token="
				+ component_access_token + ", expires_in=" + expires_in + "]";
	}
	
}
