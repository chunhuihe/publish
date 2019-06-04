package com.gym.wechat.service;

import com.google.gson.Gson;
import com.gym.common.cache.JdkSerializeRedisService;
import com.gym.common.exception.ExceptionType;
import com.gym.common.util.HttpPoolUtil;
import com.gym.wechat.common.constant.Constants;
import com.gym.wechat.common.exception.PlatformException;
import com.gym.wechat.common.util.weixin.AesException;
import com.gym.wechat.common.util.weixin.WXBizMsgCrypt;
import com.gym.wechat.domain.ComponentAccessToken;
import com.gym.wechat.inout.out.ComponentAccessTokenVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorizeService {

	@Autowired
	private EntityManager em;
	@Autowired
	private JdkSerializeRedisService jdkSerializeRedisService;
	@Autowired
	private Environment en;
	private Logger logger = LoggerFactory.getLogger(AuthorizeService.class);
	
	public boolean saveVerifyTicket(String xmlText){
		String token = en.getProperty("authorize_token");
		String aesKey = en.getProperty("aes_key");
		String appId = en.getProperty("app_id");
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
			
		} catch (AesException e) {
			logger.error("生成WXBizMsgCrypt对象异常：" +e);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * getComponentAccessToken:获取ComponentAccessToken  <br/>
	 * @author jieming.wang@fullshare.biz
	 * @return
	 */
	public void getComponentAccessToken(HttpServletRequest request){
		
		ComponentAccessTokenVo componentAccessTokenVo = new ComponentAccessTokenVo();
		// redis 取componentAccessToken
		String componentAccessToken = (String) jdkSerializeRedisService.getCacheValue(Constants.COMPONET_ACCESS_TOKEN);
		/**
		 * 如果为空或者过期，就去微信取(参数：appid，appsecret，ticket)
		 */
		if (componentAccessToken == null) {
			String appId = en.getProperty("app_id");
			String appsecret = en.getProperty("app_secret");
			String ticket =  (String) jdkSerializeRedisService.getCacheValue(Constants.COMPONENT_VERIFY_TICKET);
			//如果获取不到ticket，报异常
			if(ticket == null){
				throw new PlatformException(ExceptionType.TOTKEN_NULL);
			}
			
			String url = Constants.WEIXIN_COMPONET_ACCESS_TOKEN_URL;
			Map<String,String> params = new HashMap<String, String>();
			params.put("component_appid", appId);
			params.put("component_appsecret", appsecret);
			params.put("component_verify_ticket", ticket);
			String resp = HttpPoolUtil.httpPost(url, params);
			ComponentAccessToken respJson = new Gson().fromJson(resp, ComponentAccessToken.class);

			//如果微信获取不到token报异常
			if (respJson == null) {
				throw new  PlatformException(ExceptionType.TOTKEN_NULL);
			}
			
			componentAccessToken = respJson.getComponent_access_token();
			int timeout = respJson.getExpires_in();
			jdkSerializeRedisService.setCacheValue(Constants.COMPONET_ACCESS_TOKEN, componentAccessToken, timeout);
		}
		componentAccessTokenVo.setComponentAccessToken(componentAccessToken);

		
	}



	/**
	 *
	 * getComponentAccessToken:获取ComponentAccessToken  <br/>
	 * @author jieming.wang@fullshare.biz
	 * @return
	 */
	public ComponentAccessTokenVo getComponentAccessToken(){

		ComponentAccessTokenVo componentAccessTokenVo = new ComponentAccessTokenVo();
		// redis 取componentAccessToken
		String componentAccessToken = (String) jdkSerializeRedisService.getCacheValue(Constants.COMPONET_ACCESS_TOKEN);
		/**
		 * 如果为空或者过期，就去微信取(参数：appid，appsecret，ticket)
		 */
		if (componentAccessToken == null) {
			String appId = en.getProperty("app_id");
			String appsecret = en.getProperty("app_secret");
			String ticket =  (String) jdkSerializeRedisService.getCacheValue(Constants.COMPONENT_VERIFY_TICKET);
			//如果获取不到ticket，报异常
			if(ticket == null){
				throw new  PlatformException(ExceptionType.IVLID_ERROR);
			}

			String url = Constants.WEIXIN_COMPONET_ACCESS_TOKEN_URL;
			Map<String,String> params = new HashMap<String, String>();
			params.put("component_appid", appId);
			params.put("component_appsecret", appsecret);
			params.put("component_verify_ticket", ticket);
			String resp = HttpPoolUtil.httpPost(url, params);
			ComponentAccessToken respJson = new Gson().fromJson(resp, ComponentAccessToken.class);

			//如果微信获取不到token报异常
			if (respJson == null) {
				throw new  PlatformException(ExceptionType.TOTKEN_NULL);
			}

			componentAccessToken = respJson.getComponent_access_token();
			int timeout = respJson.getExpires_in();
			jdkSerializeRedisService.setCacheValue(Constants.COMPONET_ACCESS_TOKEN, componentAccessToken, timeout);
		}
		componentAccessTokenVo.setComponentAccessToken(componentAccessToken);
		return componentAccessTokenVo;


	}


}
