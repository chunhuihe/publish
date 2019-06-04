package com.gym.wechat.common.cache;

import com.alibaba.fastjson.JSON;

import com.gym.common.cache.JdkSerializeRedisService;
import com.gym.common.exception.CmsException;
import com.gym.common.util.HttpPoolUtil;
import com.gym.wechat.common.exception.WechatExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午3:13:37 类说明
 */
@Component
public class WechatTokenCache {

	private static final String TOKEN_URL_FORMAT = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";


	@Autowired
	private JdkSerializeRedisService jdkSerializeRedisService;

	private static Logger logger = LoggerFactory.getLogger(WechatTokenCache.class);
	public String getAccessToken(String wechaAppId, String appSecret) {
		return this.getAccessToken(wechaAppId, appSecret,false);
	}

	public String getAccessToken(String wechaAppId, String appSecret,boolean isNew) {
		String key = this.getWechatTokenKey(wechaAppId);
		WechatTokenResp resp = (WechatTokenResp) this.jdkSerializeRedisService.getCacheValue(key);
		if (!isNew&&resp != null &&  this.getTimeOut(resp).intValue()< resp.getExpires_in()) {
			return resp.getAccess_token();
		} else {
			String url = String.format(TOKEN_URL_FORMAT, wechaAppId, appSecret);
			logger.info("微信获取token的url:{}", url);
			try {
				String json = HttpPoolUtil.doGetSSL(url);
				logger.info("微信获取token的返回结果:{}", json);
				resp = JSON.parseObject(json, WechatTokenResp.class);
				resp.setLastTime(System.currentTimeMillis());
				if(resp.getErrmsg()==null){
					this.jdkSerializeRedisService.setCacheValue(key, resp, resp.getExpires_in()-3600);
					return resp.getAccess_token();
				}else{
					throw new CmsException(resp.getErrcode(),resp.getErrmsg());
				}
			} catch (Exception e) {
				logger.error("微信获取token系统内部异常：{}", e);
				throw new CmsException(WechatExceptionType.GET_WECHAT_ACCTOKEN_ERROR);
			}
		}
	}
	
	private Long getTimeOut(WechatTokenResp resp){
		long now=System.currentTimeMillis();
		Long time=now - resp.getLastTime();
		return time=time/1000;
	}

	private String getWechatTokenKey(String wechaAppId) {
		String key=this.getClass().getName()+".getWechatTokenKey()+:"+wechaAppId;
		return key;
	}

}
