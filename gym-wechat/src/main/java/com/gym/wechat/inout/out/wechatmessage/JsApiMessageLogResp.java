package com.gym.wechat.inout.out.wechatmessage; 

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月21日 上午9:47:20 
 * 类说明 
 */
public class JsApiMessageLogResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2780045668455921226L;
	
	@ApiModelProperty(notes="消息id")
	private Long messsageLogId;
	@ApiModelProperty(notes="微信appid")
	private String wechatAppId;
	@ApiModelProperty(notes="微信消息模板id")
	private String templateId;
	@ApiModelProperty(notes="keyword1")
	private String keyword1;
	@ApiModelProperty(notes="keyword2")
	private String keyword2;
	@ApiModelProperty(notes="keyword3")
	private String keyword3;
	@ApiModelProperty(notes="keyword4")
	private String keyword4;
	@ApiModelProperty(notes="送达人数")
	private Integer sendNum;
	@ApiModelProperty(notes="0=未开始，1=进行中，2=已经完成")
	private Integer stutus;
	public Long getMesssageLogId() {
		return messsageLogId;
	}
	public void setMesssageLogId(Long messsageLogId) {
		this.messsageLogId = messsageLogId;
	}
	public String getWechatAppId() {
		return wechatAppId;
	}
	public void setWechatAppId(String wechatAppId) {
		this.wechatAppId = wechatAppId;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public String getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}
	public String getKeyword4() {
		return keyword4;
	}
	public void setKeyword4(String keyword4) {
		this.keyword4 = keyword4;
	}
	public Integer getSendNum() {
		return sendNum;
	}
	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}
	public Integer getStutus() {
		return stutus;
	}
	public void setStutus(Integer stutus) {
		this.stutus = stutus;
	}
	
	
	


}
 