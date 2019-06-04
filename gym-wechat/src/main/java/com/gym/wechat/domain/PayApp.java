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
@Table(name = "pay_app")
public class PayApp implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//主键
	//255
	@Id 
	@Column(name = "app_id")
	private String appId;
	//255
	@Column(name = "app_key")
	private String appKey;
	//0=未启用，1=已经启用
	@NotNull @Max(127)
	@Column(name = "enable")
	private Integer enable;
	//RSA密钥对 公钥
	@Column(name = "rsa_public_key")
	private String rsaPublicKey;
	//RSA密钥对 私钥
	@Column(name = "rsa_private_key")
	private String rsaPrivateKey;
	// 客户对应的ID
	@Column(name = "client_id")
	private Long clientId;
	//创建时间
	@NotNull 
	@Column(name = "created_time")
	private Date createdTime;
	//修改时间
	@NotNull 
	@Column(name = "modifyed_time")
	private Date modifyedTime;
	//columns END
	
	
	public String getAppId() {
		return this.appId;
	}
	
	public void setAppId(String value) {
		this.appId = value;
	}
	
	
	public String getAppKey() {
		return this.appKey;
	}
	
	public void setAppKey(String value) {
		this.appKey = value;
	}
	
	
	public Integer getEnable() {
		return this.enable;
	}
	
	public void setEnable(Integer value) {
		this.enable = value;
	}
	
	
	public String getRsaPublicKey() {
		return this.rsaPublicKey;
	}
	
	public void setRsaPublicKey(String value) {
		this.rsaPublicKey = value;
	}
	
	
	public String getRsaPrivateKey() {
		return this.rsaPrivateKey;
	}
	
	public void setRsaPrivateKey(String value) {
		this.rsaPrivateKey = value;
	}
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	public static PayApp findByClientId(EntityManager em, Long clientId) {
		String hql = "select po from PayApp po where po.clientId=:clientId";
		TypedQuery<PayApp> query = em.createQuery(hql, PayApp.class);
		query.setParameter("clientId", clientId);
		List<PayApp> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}

