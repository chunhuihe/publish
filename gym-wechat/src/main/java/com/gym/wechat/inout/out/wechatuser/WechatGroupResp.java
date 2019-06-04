package com.gym.wechat.inout.out.wechatuser; 

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月22日 下午4:20:17 
 * 类说明 
 */
public class WechatGroupResp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 413568633248194227L;

	private String openGId;
	
	private WechatAppResp watermark;

	public String getOpenGId() {
		return openGId;
	}

	public void setOpenGId(String openGId) {
		this.openGId = openGId;
	}

	public WechatAppResp getWatermark() {
		return watermark;
	}

	public void setWatermark(WechatAppResp watermark) {
		this.watermark = watermark;
	}
	
	
	

}
 