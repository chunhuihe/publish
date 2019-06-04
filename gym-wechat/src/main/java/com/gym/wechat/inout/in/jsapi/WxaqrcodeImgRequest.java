package com.gym.wechat.inout.in.jsapi;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2018年1月15日 上午11:49:31 类说明
 */
public class WxaqrcodeImgRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3403572007804082333L;

	@ApiModelProperty(notes = "分配的appId")
	@NotBlank(message = "appId不能为空")
	private String appId;
	@ApiModelProperty(notes = "生成二维码的路径")
	@NotBlank(message = "path不能为空")
	private String path;
	@ApiModelProperty(notes = "宽度")
	@Min(value = 1, message = "width>1")
	private int width;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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
