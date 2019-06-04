package com.gym.wechat.inout.in.wechatmessage; 

import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;


/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月27日 下午3:58:40 
 * 类说明 
 */
public class TemplateAppRequest extends BaseRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4595569790873406373L;
	/**
	 * 
	 */
	
	
	@ApiModelProperty(notes="唯一标识，发送成功后将不会再发送")
	@NotBlank
	private String reqId;
	@ApiModelProperty(notes="memberId")
	private long memberId;
	@ApiModelProperty(notes="是	所需下发的模板消息的id")
	@NotBlank(message="模板id不能为空")
	private String template_id;
	@ApiModelProperty(notes="否	点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。")
	private String page="pages/index/index";
	@ApiModelProperty(notes="参数")
	private TemplateData data;
	
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public TemplateData getData() {
		return data;
	}
	public void setData(TemplateData data) {
		this.data = data;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	
}
 