package com.gym.wechat.common.cache;

import java.io.Serializable;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午3:17:06 类说明
 */
public class WechatTokenResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1458427259575320195L;
	private String access_token;// 获取到的凭证
	private int expires_in;// 凭证有效时间，单位：秒

	private int errcode;

	private String errmsg;
	
	private long lastTime;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

}
