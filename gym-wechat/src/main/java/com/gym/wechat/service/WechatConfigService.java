package com.gym.wechat.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.common.constant.StatusConstant;
import com.gym.common.exception.BaseException;
import com.gym.wechat.common.exception.WechatExceptionType;
import com.gym.wechat.domain.PayApp;
import com.gym.wechat.domain.PayWechatConfig;


/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月18日 上午10:07:53 类说明
 */
@Service
public class WechatConfigService {

	@Autowired
	private EntityManager em;


	

	/**
	 * 获取PayApp
	 * 
	 * @param appId
	 * @return
	 */
	public PayApp getPayApp(String appId) {
		PayApp payApp = em.find(PayApp.class, appId);
		if (payApp == null) {
			throw new BaseException(WechatExceptionType.APP_ID_NULL);
		}
		if (payApp.getEnable() != null && payApp.getEnable().equals(StatusConstant.STATUS_INVALID)) {
			throw new BaseException(WechatExceptionType.APP_ID_ENABLE_DOWN);
		}
		return payApp;
	}



	/**
	 * 获取app微信支付配置信息
	 * 
	 * @param appId
	 * @return
	 */
	public PayWechatConfig getWechatPayConfig(String appId) {
		PayWechatConfig payWechatConfig = em.find(PayWechatConfig.class, appId);
		if (payWechatConfig == null) {
			throw new BaseException(WechatExceptionType.PAY_CONFIG_NULL);
		}
		if (payWechatConfig.getEnable() != null && payWechatConfig.getEnable().equals(StatusConstant.STATUS_INVALID)) {
			throw new BaseException(WechatExceptionType.PAY_CONFIG_NULL);
		}
		return payWechatConfig;
	}

	/**
	 * 根据分享的参数获取微信配置
	 * @param appUserName
	 * @return
	 */
	public PayWechatConfig getWechatPayConfigByAppUserName(String appUserName) {
		PayWechatConfig payWechatConfig = PayWechatConfig.findByAppUserName(em, appUserName);
		if (payWechatConfig == null) {
			throw new BaseException(WechatExceptionType.PAY_CONFIG_NULL);
		}
		if (payWechatConfig.getEnable() != null && payWechatConfig.getEnable().equals(StatusConstant.STATUS_INVALID)) {
			throw new BaseException(WechatExceptionType.PAY_CONFIG_NULL);
		}
		return payWechatConfig;
	}
}
