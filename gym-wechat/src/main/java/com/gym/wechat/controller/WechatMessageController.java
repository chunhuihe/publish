package com.gym.wechat.controller;

import com.gym.common.inout.BaseResponse;
import com.gym.wechat.inout.in.wechatmessage.FormRequest;
import com.gym.wechat.inout.in.wechatmessage.JsApiMessageLogRequest;
import com.gym.wechat.inout.in.wechatmessage.TemplateAppRequest;
import com.gym.wechat.inout.in.wechatmessage.TemplateRequest;
import com.gym.wechat.service.JsApiService;
import com.gym.wechat.service.WechatMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月15日 上午10:23:13 类说明
 */
@RestController
@RequestMapping("/wechat")
@Api("微信消息模块")
public class WechatMessageController {
	
	@Autowired
	private WechatMessageService wechatMessageService;
	@Autowired
	private JsApiService jsApiService;




	@RequestMapping(value = "/1.0.0/form/add", method = RequestMethod.POST)
	@ApiOperation(value = "添加form事件", notes = "添加form事件")
	public  BaseResponse<?> addForm(@RequestBody FormRequest request){
		this.wechatMessageService.addForm(request);
		return BaseResponse.valueOfSuccess();
	}
	@RequestMapping(value = "/1.0.0/jsApiMessageLog/get", method = RequestMethod.POST)
	@ApiOperation(value = "获取用小程序发送记录", notes = "获取用小程序发送记录")
	public  BaseResponse<?> getJsApiMessageLog(@RequestBody JsApiMessageLogRequest request){
		return null;
	}

	@RequestMapping(value = "/1.0.0/template/send", method = RequestMethod.POST)
	@ApiOperation(value = "用小程序发送模板消息", notes = "用小程序发送模板消息")
	public  BaseResponse<?> sendTemplate(@RequestBody TemplateAppRequest request, @RequestParam("appId")String appId){
		TemplateRequest req=new TemplateRequest();
		BeanUtils.copyProperties(request, req);
		this.wechatMessageService.sendTemplate(appId, req);
		return BaseResponse.valueOfSuccess();
	}

	@RequestMapping(value = "/2.0.0/template/send", method = RequestMethod.POST)
	@ApiOperation(value = "用小程序发送模板消息(根据不同的clientId)", notes = "用小程序发送模板消息(根据不同的clientId)")
	public  BaseResponse<?> sendTemplate(@RequestBody TemplateAppRequest request, @RequestParam("clientId")Long clientId){
		TemplateRequest req=new TemplateRequest();
		BeanUtils.copyProperties(request, req);
		this.wechatMessageService.sendTemplate(clientId, req);
		return BaseResponse.valueOfSuccess();
	}
	
	@RequestMapping(value = "/1.0.0/message/{appId}/callback", method = RequestMethod.POST)
	@ApiOperation(value = "添加form事件", notes = "添加form事件")
	public  BaseResponse<?> messageCallback(){
		return BaseResponse.valueOfSuccess();
	}

}
