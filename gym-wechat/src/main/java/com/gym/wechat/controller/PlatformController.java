package com.gym.wechat.controller;

import com.gym.common.inout.BaseResponse;
import com.gym.wechat.inout.out.ComponentAccessTokenVo;
import com.gym.wechat.service.AuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月15日 上午10:23:13 类说明
 */
@Controller
@RequestMapping("/wechat")
@Api("微信平台模块")
public class PlatformController {

	@Autowired
	AuthorizeService authorizeService;
	private Logger logger = LoggerFactory.getLogger(AuthorizeService.class);

	/**
	 * 微信授权事件的接受
	 * @return
	 */
	@ApiOperation(value = "微信通知授权" , notes = "微信通知授权")
	@RequestMapping(value="/authorize",method={RequestMethod.GET,RequestMethod.POST})
	public void acceptAuthorizeEvent(
			HttpServletResponse response, HttpServletRequest request){
		try {
			//处理授权事件
			//authorizeService.getComponentAccessToken(request);
			PrintWriter pw = response.getWriter();
			pw.write("success");
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "接受微信推送的" , notes = "异常码如：")
	@RequestMapping(value = "/recive", method = {RequestMethod.GET,RequestMethod.POST})
	public String reciveTicket(@RequestBody String param){
		logger.info(param);
		return "success";
	}



	@ApiOperation(value = "获取component_access_token" , notes = "获取第三方token")
	@RequestMapping(value = "/get_component_access_token", method = RequestMethod.GET)
	public BaseResponse<ComponentAccessTokenVo> getComponentAccessToken(@RequestBody String param){
		ComponentAccessTokenVo componentAccessTokenVo = authorizeService.getComponentAccessToken();
		BaseResponse<ComponentAccessTokenVo> resp = new BaseResponse<ComponentAccessTokenVo>();
		resp.setData(componentAccessTokenVo);
		return resp;
	}
	
}
