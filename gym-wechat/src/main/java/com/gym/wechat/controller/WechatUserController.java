package com.gym.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.inout.BaseResponse;
import com.gym.wechat.inout.in.wechatuser.WechatOpenIdRequest;
import com.gym.wechat.inout.out.wechatuser.WechatOpenIdResp;
import com.gym.wechat.service.WechatUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月15日 上午10:23:13 类说明
 */
@Controller
@RequestMapping("/wechat")
@Api("微信用户模块")
public class WechatUserController {

	@Autowired
	private WechatUserService wechatUserService;


	@RequestMapping(value = "/1.0.0/token", method = RequestMethod.POST)
	@ApiOperation(value = "用户授权", notes = "用户授权")
	public @ResponseBody
	BaseResponse<WechatOpenIdResp> getOpenId(@RequestBody WechatOpenIdRequest request) {
		WechatOpenIdResp data = this.wechatUserService.getUserInfo(request);
		return BaseResponse.valueOfSuccess(data);
	}

	@RequestMapping(value = "/1.0.0/tokenGet", method = RequestMethod.POST)
	@ApiOperation(value = "用户授权", notes = "用户授权")
	public @ResponseBody
	BaseResponse<Boolean> getCoach() throws InterruptedException {
		 this.wechatUserService.getCoach();
		return BaseResponse.valueOfSuccess(true);
	}
	@RequestMapping(value = "/1.0.0/tokenGetOne", method = RequestMethod.POST)
	@ApiOperation(value = "用户授权", notes = "用户授权")
	public @ResponseBody
	BaseResponse<Boolean> tokenGetOne(@RequestBody Integer id) throws InterruptedException {
		this.wechatUserService.getCoach(id);
		return BaseResponse.valueOfSuccess(true);
	}
}
