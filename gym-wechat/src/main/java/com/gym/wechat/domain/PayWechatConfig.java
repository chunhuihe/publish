package com.gym.wechat.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pay_wechat_config")
public class PayWechatConfig implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	// appId
	@Id
	@Column(name = "app_id")
	private String appId;
	// 微信支付分配的企业号id
	@Column(name = "wechat_app_id")
	private String wechatAppId;
	// 微信支付分配的商户号
	@Column(name = "wechat_mch_id")
	private String wechatMchId;
	// 微信支付提供的商户号
	@Column(name = "wechat_mch_key")
	private String wechatMchKey;
	// 0=未启用，1=已经启用
	@NotNull
	@Max(127)
	@Column(name = "enable")
	private Integer enable;
	// 创建时间
	@NotNull
	@Column(name = "created_time")
	private Date createdTime;
	// 修改时间
	@NotNull
	@Column(name = "modifyed_time")
	private Date modifyedTime;
	@Column(name = "wechat_pro_key")
	private String wechatProKey;
	// 签名类型
	@Column(name = "sign_type")
	private String signType;
	
	@Column(name = "jsapi_message_token")
	private String jsapiMessageToken;
	
	@Column(name = "cert_path")
	private String certPath;
	
	@Column(name = "wechat_username")
	private String wechatUserName;

	// columns END

	public PayWechatConfig() {
	}

	public String getJsapiMessageToken() {
		return jsapiMessageToken;
	}

	public void setJsapiMessageToken(String jsapiMessageToken) {
		this.jsapiMessageToken = jsapiMessageToken;
	}

	public PayWechatConfig(String appId) {
		this.appId = appId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public void setAppId(String value) {
		this.appId = value;
	}

	public String getWechatProKey() {
		return wechatProKey;
	}

	public void setWechatProKey(String wechatProKey) {
		this.wechatProKey = wechatProKey;
	}

	public String getAppId() {
		return this.appId;
	}

	public String getWechatAppId() {
		return this.wechatAppId;
	}

	public void setWechatAppId(String value) {
		this.wechatAppId = value;
	}

	public String getWechatMchId() {
		return this.wechatMchId;
	}

	public void setWechatMchId(String value) {
		this.wechatMchId = value;
	}

	public String getWechatMchKey() {
		return this.wechatMchKey;
	}

	public void setWechatMchKey(String value) {
		this.wechatMchKey = value;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer value) {
		this.enable = value;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date value) {
		this.createdTime = value;
	}

	public Date getModifyedTime() {
		return this.modifyedTime;
	}

	public void setModifyedTime(Date value) {
		this.modifyedTime = value;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public String getWechatUserName() {
		return wechatUserName;
	}

	public void setWechatUserName(String wechatUserName) {
		this.wechatUserName = wechatUserName;
	}
	
	public static PayWechatConfig findByAppUserName(EntityManager em, String appUserName){
		String hql = "select po from PayWechatConfig po where po.wechatUserName=:appUserName";
		TypedQuery<PayWechatConfig> query = em.createQuery(hql, PayWechatConfig.class);
		query.setParameter("appUserName", appUserName);
		List<PayWechatConfig> list = query.getResultList();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
