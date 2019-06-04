package com.gym.wechat.controller;

import com.gym.common.inout.BaseResponse;
import com.gym.wechat.inout.in.jsapi.WxaQRCodeImgRequestV2;
import com.gym.wechat.inout.in.jsapi.WxaQRCodeImgRequestV3;
import com.gym.wechat.inout.in.jsapi.WxaqrcodeImgRequest;
import com.gym.wechat.service.JsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2018年1月15日 上午11:32:20 
 * 类说明 
 */

@Controller
@RequestMapping(value = "/wechat")
@Api("小程序公共模块")
public class JsApiController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JsApiService jsApiService;
	

	@RequestMapping(value = "/jsapi/", method = RequestMethod.POST)
	@ApiOperation(value = "小程序生成二维码图片", notes = "小程序生成二维码图片")
	public @ResponseBody
    BaseResponse<String> getAccess(@RequestBody WxaqrcodeImgRequest request) {
		String data=this.jsApiService.getWxaqrcodeImg(request.getAppId(), request.getPath(), request.getWidth());
		return BaseResponse.valueOfSuccess(data);
	}
	
	@RequestMapping(value = "/2.0.0/jsapi", method = RequestMethod.POST)
	@ApiOperation(value = "小程序生成二维码图片V2", notes = "小程序生成二维码图片V2")
	public @ResponseBody
    BaseResponse<String> getQRCodeImg(@RequestBody WxaQRCodeImgRequestV2 request) {
		String data=this.jsApiService.getQRCodeImg(request.getAppUserName(), request.getPath(), request.getWidth());
		return BaseResponse.valueOfSuccess(data);
	}
	@RequestMapping(value = "/3.0.0/jsapi", method = RequestMethod.POST)
	@ApiOperation(value = "小程序生成二维码图片V3", notes = "小程序生成二维码图片V2")
	public @ResponseBody
	BaseResponse<String> getQRCodeImg(@RequestBody WxaQRCodeImgRequestV3 request) {
		String data=this.jsApiService.getQRCodeImg(request.getAppUserName(), request.getPath(), request.getWidth(),request.getIsclear());
		return BaseResponse.valueOfSuccess(data);
	}
}
 