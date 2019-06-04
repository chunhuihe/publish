package com.gym.wechat.service;

import com.gym.common.cache.JdkSerializeRedisService;
import com.gym.common.cache.JsonSerializeRedisService;
import com.gym.common.util.HttpPoolUtil;
import com.gym.wechat.common.cache.WechatTokenCache;
import com.gym.wechat.common.constant.AppId;
import com.gym.wechat.common.util.WeixinSignUtil;
import com.gym.wechat.domain.PayWechatConfig;
import com.gym.wechat.inout.in.wechatmessage.MediaResp;
import com.gym.wechat.service.handler.JsApiHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月17日 下午6:02:31 类说明
 */
@Service
public class JsApiService {

	@SuppressWarnings("unused")
	private static final String UPLOAD_MEDIA_FORMAT = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=image";

	private static final String SEND_CUSTOM_FORMAT = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	@Autowired
	private WechatConfigService wechatConfigService;

	@Autowired
	private JsonSerializeRedisService jsonSerializeRedisService;
	@Autowired
	private JdkSerializeRedisService jdkSerializeRedisService;

	@Autowired
	private WechatTokenCache wechatTokenCache;
	
	@Autowired
	private JsApiHandler jsApiHandler;

	@Value("${jsapi.media.file.path}")
	private String jsapiMediaFilePath;
	
	

	private Logger logger = LoggerFactory.getLogger(getClass());

	public boolean checkMessageAccess(String appId, String signature, String timestamp, String nonce) {
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(appId);
		if (config != null) {
			return WeixinSignUtil.checkSignature(signature, timestamp, nonce, config.getJsapiMessageToken());
		}
		return false;
	}

//	public Map<String, String> uploadMeida(String appId, String filePath) {
//		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(appId);
//		if (config == null) {
//			throw new CmsException(WechatExceptionType.APP_ID_NULL);
//		}
//		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
//		String url = String.format(UPLOAD_MEDIA_FORMAT, token);
//		String body = this.uploadFile(url, filePath);
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		String mapKey = DateUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
//		map.put(mapKey, body);
//		this.jsonSerializeRedisService.haset(JsApiService.class.getName() + ":uploadMeida()", map);
//		return this.jsonSerializeRedisService.getClusterInterface().hgetAll(JsApiService.class.getName() + ":uploadMeida");
//	}

//	private String uploadFile(String url, String filePath) {
//		Map<String, String> textMap = new HashMap<String, String>();
//		Map<String, String> fileMap = new HashMap<String, String>();
//		fileMap.put("upfile", filePath);
//		String contentType = "image";// image/png
//		String ret = HttpUploadFile.formUpload(url, textMap, fileMap, contentType);
//		return ret;
//	}

//	public MediaResp reSendMedia() {
//		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(AppId.PINTUAN);
//		if (config == null) {
//			throw new CmsException(WechatExceptionType.APP_ID_NULL);
//		}
//		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
//		String url = String.format(UPLOAD_MEDIA_FORMAT, token);
//
//		String path = this.getClass().getResource("/20171123173308.jpg").getFile();
//		if (System.getProperty("file.separator").equals("\\")) {
//			path = path.substring(1, path.length());
//		} else {
//			path = jsapiMediaFilePath;
//		}
//		String body = this.uploadFile(url, path);
//		MediaResp resp = JSON.parseObject(body, MediaResp.class);
//		this.jdkSerializeRedisService.setCacheValue(MediaResp.class.getName(), resp, 60 * 60 * 24 * 3);
//		return resp;
//	}

	public void sendCustom(String openId) {
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(AppId.PINTUAN);
		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
		String url = String.format(SEND_CUSTOM_FORMAT, token);
		String bodyFormat = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		MediaResp resp = (MediaResp) this.jdkSerializeRedisService.getCacheValue(MediaResp.class.getName());
		if (resp == null) {
			resp = new MediaResp();
			resp.setMedia_id("QaOGRt1_nBsHAKo01kxOwsDcwC9KrtspFiGPMXbJEDw-chqfH5ibFGXxDJMGE3Q2");
		}
		String outputStr = String.format(bodyFormat, openId, resp.getMedia_id());
		try {
			String body = HttpPoolUtil.doPostSSL(url, outputStr, null, 0);

			logger.info("发送消息返回：{}测试", body);
		} catch (Exception e) {
			logger.error("发送模板消息失败：{}", e);
		}
	}

	/**
	 * 获取二维码图片
	 * @param appId
	 * @param path
	 * @param width
	 * @return
	 */
	public String getWxaqrcodeImg(String appId,String path,int width){
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(appId);
		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
		return this.jsApiHandler.getWxaqrcodeImg(token, path, width);
	}
	
	/**
	 * 根据分享参数获取二维码图片
	 * @param appUserName
	 * @param path
	 * @param width
	 * @return
	 */
	public String getQRCodeImg(String appUserName, String path, int width){
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfigByAppUserName(appUserName);
		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
		return this.jsApiHandler.getWxaqrcodeImg(token, path, width);
	}


	/**
	 * 根据分享参数获取二维码图片
	 * @param appUserName
	 * @param path
	 * @param width
	 * @return
	 */
	public String getQRCodeImg(String appUserName, String path, int width,Boolean isclear){
		PayWechatConfig config = this.wechatConfigService.getWechatPayConfigByAppUserName(appUserName);
		String token = wechatTokenCache.getAccessToken(config.getWechatAppId(), config.getWechatProKey());
		return this.jsApiHandler.getWxaqrcodeImg(token, path, width,isclear);
	}
	/*
	 * @PostConstruct public void test(){ //this.reSendMedia();
	 * this.sendCustom("oylv10BXltDsCaB9W_hB8qHRW8nA"); }
	 */
}
