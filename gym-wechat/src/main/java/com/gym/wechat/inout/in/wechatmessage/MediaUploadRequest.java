package com.gym.wechat.inout.in.wechatmessage;

import com.gym.common.inout.BaseRequest;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月23日 下午5:22:39 
 * 类说明 
 */
public class MediaUploadRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1526636539902721543L;

	private String appId;
	
	private String filePath;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

}
 