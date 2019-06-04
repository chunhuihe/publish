package com.gym.wechat.inout.in.wechatuser; 

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;

import io.swagger.annotations.ApiModelProperty;


/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月8日 下午4:12:45 
 * 类说明 
 */
public class WechatOpenIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358943452076893157L;
	
	
	@NotBlank(message="appId不能为空")
	@ApiModelProperty(notes="appId")
	private String appId;
	@NotBlank(message="jsCode不能为空")
	@ApiModelProperty(notes="jsCode")
	private String jsCode;
	@NotBlank(message="昵称不能为空")
	@ApiModelProperty(notes="昵称")
	private String nickname;
	@ApiModelProperty(notes="用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
	private Integer sex;
	@ApiModelProperty(notes="省份")
	private String province;
	@ApiModelProperty(notes="城市")
	private String city;
	@ApiModelProperty(notes="国家")
	private String country;
	@ApiModelProperty(notes="头像")
	private String headimgurl;
	@ApiModelProperty(notes="用户授权信息")
	private String privilege;
	@ApiModelProperty(notes="来源")
	private String source;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	

}
 