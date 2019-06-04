package com.gym.wechat.inout.in.wechatmessage; 

import org.hibernate.validator.constraints.NotBlank;

import com.gym.common.inout.BaseRequest;

import io.swagger.annotations.ApiModelProperty;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午3:55:06 
 * 类说明 
 */
public class TemplateRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -562825811605174306L;
	
	private String reqId;
	@ApiModelProperty(notes="memberId")
	private long memberId;
	@ApiModelProperty(notes="是	接收者（用户）的 openid")
	private String touser;
	@ApiModelProperty(notes="是	所需下发的模板消息的id")
	@NotBlank(message="模板id不能为空")
	private String template_id;
	@ApiModelProperty(notes="否	点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。")
	private String page="pages/index/index";
	@ApiModelProperty(notes="表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id")
	private String form_id;	
	@ApiModelProperty(notes="参数")
	private TemplateData data;
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
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
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
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
	
	

}
 