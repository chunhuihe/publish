package com.gym.wechat.inout.in.wechatmessage; 

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;

import io.swagger.annotations.ApiModelProperty;


/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午5:04:41 
 * 类说明 
 */
public  class FormRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8512484878460826061L;
	
	@ApiModelProperty(notes="openId")
	@NotBlank(message="openId不能为空")
	private String openId;
	
	
	@ApiModelProperty(notes="表单或者支付id")
	@NotBlank(message="openId不能为空")
	private String formId;
	
	@ApiModelProperty(notes="0=普通表单，1=支付")
	@com.gym.common.vali.Enum(values={"0","1"})
	private int fromType;
	
	@ApiModelProperty(notes="appId")
	private String appId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public int getFromType() {
		return fromType;
	}

	public void setFromType(int fromType) {
		this.fromType = fromType;
	}

	

}
 