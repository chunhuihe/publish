package com.gym.wechat.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "wechat_message")
public class WechatMessage implements java.io.Serializable{
	
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//wechatMessageId
	@Id
	@Column(name = "wechat_message_id")
	private Long wechatMessageId;
	//app请求参数
	@Column(name = "app_request")
	private String appRequest;
	//微信请求参数
	@Column(name = "wechat_reqeust")
	private String wechatReqeust;
	//微信返回结果
	@Column(name = "wechat_response")
	private String wechatResponse;
	//模板id
	@Column(name = "tem_id")
	private String temId;
	//0=未成功，1=成功
	@NotNull @Max(127)
	@Column(name = "status")
	private Integer status;
	//创建时间
	@NotNull 
	@Column(name = "create_time")
	private Date createTime;
	//columns END



	
	
	
	public Long getWechatMessageId() {
		return this.wechatMessageId;
	}
	
	public void setWechatMessageId(Long value) {
		this.wechatMessageId = value;
	}
	
	
	public String getAppRequest() {
		return this.appRequest;
	}
	
	public void setAppRequest(String value) {
		this.appRequest = value;
	}
	
	
	public String getWechatReqeust() {
		return this.wechatReqeust;
	}
	
	public void setWechatReqeust(String value) {
		this.wechatReqeust = value;
	}
	
	
	public String getWechatResponse() {
		return this.wechatResponse;
	}
	
	public void setWechatResponse(String value) {
		this.wechatResponse = value;
	}
	
	
	public String getTemId() {
		return this.temId;
	}
	
	public void setTemId(String value) {
		this.temId = value;
	}
	
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	

	
	
}

