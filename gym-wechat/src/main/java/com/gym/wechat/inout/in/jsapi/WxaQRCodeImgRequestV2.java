package com.gym.wechat.inout.in.jsapi;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;

public class WxaQRCodeImgRequestV2 extends BaseRequest {
	private static final long serialVersionUID = -3210889716522874111L;
	@ApiModelProperty(notes = "分享的appUserName")
	@NotBlank(message = "appUserName不能为空")
	private String appUserName;
	@ApiModelProperty(notes = "生成二维码的路径")
	@NotBlank(message = "path不能为空")
	private String path;
	@ApiModelProperty(notes = "宽度")
	@Min(value = 1, message = "width>1")
	private int width;
	public String getAppUserName() {
		return appUserName;
	}
	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
