package com.gym.wechat.inout.in.wechatmessage; 

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月23日 下午6:57:09 
 * 类说明 
 */
public class MediaResp implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8935931810105354517L;

	private String type;
	
	private String media_id;
	
	private long created_at;

	private String errmsg;
	
	private int errcode;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	
	
}
 