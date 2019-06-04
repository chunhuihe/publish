package com.gym.wechat.service;

import com.alibaba.fastjson.JSON;
import com.gym.common.cache.JdkSerializeRedisService;
import com.gym.common.exception.CmsException;
import com.gym.common.service.IDService;
import com.gym.common.util.HttpPoolUtil;
import com.gym.wechat.common.cache.WechatTokenCache;
import com.gym.wechat.common.constant.WechatFormType;
import com.gym.wechat.common.exception.WechatExceptionType;
import com.gym.wechat.domain.PayApp;
import com.gym.wechat.domain.PayWechatConfig;
import com.gym.wechat.domain.WechatForm;
import com.gym.wechat.domain.WechatMessage;
import com.gym.wechat.domain.WechatUser;
import com.gym.wechat.inout.in.wechatmessage.FormRequest;
import com.gym.wechat.inout.in.wechatmessage.TemplateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Date;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午3:48:54 类说明
 */
@Service
public class WechatMessageService {

	private static final String SEND_TEM_MESSAGE_FORMAT = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=%s";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WechatConfigService wechatConfigService;

	@Autowired
	private WechatTokenCache wechatTokenCache;

	@Autowired
	private IDService idService;

	@Autowired
	private EntityManager em;
	@Autowired
	private JdkSerializeRedisService jdkSerializeRedisService;
	
	public String getKey(String className,String appId,String reqId){
		String format="%s-%s-%s";
		return String.format(format, className,appId,reqId);
	}

	public void addWechatMessage(String appRequest, String wechatRequest, String wechatResp, int status, String temId) {
		WechatMessage message = new WechatMessage();
		message.setAppRequest(appRequest);
		message.setWechatReqeust(wechatRequest);
		message.setWechatResponse(wechatResp);
		message.setTemId(temId);
		message.setWechatMessageId(this.idService.generate());
		message.setCreateTime(new Date());
		message.setStatus(status);
		em.persist(message);
	}


	@Transactional
	public void addForm(FormRequest request) {
		WechatForm wechatForm = new WechatForm();
		wechatForm.setCreatedTime(new Date());
		wechatForm.setFormWechatId(this.idService.generate());
		wechatForm.setOpenId(request.getOpenId());
		if (WechatFormType.PRE_PAY == request.getFromType()) {
			wechatForm.setPrepayId(request.getFormId());
		} else {
			wechatForm.setFormId(request.getFormId());
		}
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(request.getAppId());
		wechatForm.setWechatAppId(config.getWechatAppId());
		em.persist(wechatForm);
	}


	@Transactional
	public void sendTemplate(String appId, TemplateRequest request) {
		String key=this.getKey(this.getClass().getName(), appId, request.getReqId());
		if(this.jdkSerializeRedisService.getCacheValue(key)!=null){
			return ;
		}
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(appId);
		if (config != null) {
			WechatUser wechatUser = WechatUser.findByWechatAppIdAndMemberId(em, config.getWechatAppId(), request.getMemberId());
			if (wechatUser == null) {
				throw new CmsException(WechatExceptionType.USER_NULL);
			}
			request.setTouser(wechatUser.getOpenId());
			WechatForm form = this.getFormId(config.getWechatAppId(), wechatUser.getOpenId());
			if (form.getFormId() != null) {
				request.setForm_id(form.getFormId());
			} else {
				request.setForm_id(form.getPrepayId());
			}
			String token = this.wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
			if (token != null) {
				String url = String.format(SEND_TEM_MESSAGE_FORMAT, token);
				logger.info("发送模板消息url:{}", url);
				String requestJson = JSON.toJSONString(request);
				String json = HttpPoolUtil.doPostSSL(url, requestJson, null, 0);
				logger.info("发送模板消息返回:{}", json);
				int status = 0;
				if (json.contains("ok")) {
					status = 1;
					this.jdkSerializeRedisService.setLockValue(key,request.getReqId(), 60*60*24*300);
				}
				this.addWechatMessage(JSON.toJSONString(request), requestJson, json, status, request.getTemplate_id());
				if ((form.getPrepayId() != null && form.getUseNum() == 2) || form.getFormId() != null) {
					this.updateFormStatus(form.getFormWechatId());
				} else {
					WechatForm.updateFormUseNum(em, form.getFormWechatId());
				}

			}
		}

	}
	
	@Transactional
	public void sendTemplate(Long clientId, TemplateRequest request) {
		String appId = null;
		PayApp payApp = PayApp.findByClientId(em, clientId);
		if(payApp!=null){
			appId = payApp.getAppId();
		}
		sendTemplate(appId, request);
	}

	private void updateFormStatus(Long formWechatId) {
		WechatForm.updateFormUseStatus(em, formWechatId);

	}

	private WechatForm getFormId(String wechatAppId, String openId) {
		WechatForm form = WechatForm.findOneByWechatAppIdAndOpenId(em, wechatAppId, openId);
		if (form == null) {
			throw new CmsException(WechatExceptionType.FORM_ID_LESS);
		}
		return form;
	}

}
