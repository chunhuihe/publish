package com.gym.wechat.service.handler;

import com.alibaba.druid.util.Base64;
import com.gym.common.exception.CmsException;
import com.gym.common.util.HttpPoolUtil;
import com.gym.wechat.common.exception.WechatExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2018年1月15日 上午11:42:11 
 * 类说明 
 */

@Component
public class JsApiHandler {
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	public String getWxaqrcodeImg(String token, String path, int width) {
		String urlFormat = "https://api.weixin.qq.com/wxa/getwxacode?access_token=%s";
		String bodyFormat = "{\"path\": \"%s\", \"width\": %s, \"is_hyaline\": false}";
		String url = String.format(urlFormat, token);
		String outputStr = String.format(bodyFormat, path, width);
		String body = null;
		try {
			byte[] resp = HttpPoolUtil.getByteDoPostSSL(url, outputStr, null, 0);
			body=Base64.byteArrayToBase64(resp);
			logger.info("发送消息返回：{}测试", body);
		} catch (Exception e) {
			logger.error("发送模板消息失败：{}", e);
			throw new CmsException(WechatExceptionType.WXARCODE_FAIL);
		}
		return body;
	}


	public String getWxaqrcodeImg(String token, String path, int width,Boolean isclear) {
		String urlFormat = "https://api.weixin.qq.com/wxa/getwxacode?access_token=%s";
		String bodyFormat="";
		if(isclear) {
			bodyFormat = "{\"path\": \"%s\", \"width\": %s, \"is_hyaline\": true}";
		}
		else
		{
			bodyFormat = "{\"path\": \"%s\", \"width\": %s, \"is_hyaline\": false}";
		}
		String url = String.format(urlFormat, token);
		String outputStr = String.format(bodyFormat, path, width);
		String body = null;
		try {
			byte[] resp = HttpPoolUtil.getByteDoPostSSL(url, outputStr, null, 0);
			body=Base64.byteArrayToBase64(resp);
			logger.info("发送消息返回：{}测试", body);
		} catch (Exception e) {
			logger.error("发送模板消息失败：{}", e);
			throw new CmsException(WechatExceptionType.WXARCODE_FAIL);
		}
		return body;
	}
}
 