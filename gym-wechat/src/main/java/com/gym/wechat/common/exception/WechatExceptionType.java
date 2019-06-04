package com.gym.wechat.common.exception;

import com.gym.common.exception.IExceptionType;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月18日 上午11:28:39 
 * 类说明 
 */
public enum WechatExceptionType implements IExceptionType{
	
	
	APP_ID_NULL(80001,"appId不存在","appId不存在"), 
	
	APP_ID_ENABLE_DOWN(80002,"appId被禁用","appId被禁用"), 
	
	PAY_CONFIG_NULL(80003,"配置不存在","配置不存在"),
	
	PAY_ALI_ENABLE_DOWN(80004,"支付宝权限被禁用","支付宝权限被禁用"),
	
	ALIPAY_ORDER_FAIL(80005,"支付宝订单生成失败","支付宝订单生成失败"), 
	
	ORDER_EXIT(80006,"订单已经存在","订单已经存在"),
	
	ALIPAY_CHECK_FAIL(80007,"支付宝回调校验错误","支付宝回调校验错误"), 
	
	PAYORDER_NULL(80008,"订单不存在","订单不存在"), 
	
	PAYORDER_ONVAIL(80009,"订单校验错误","订单校验错误"),
	
	
	
	PAYORDER_LOCK(80010,"该订单被锁住请稍后","该订单被锁住请稍后"), 
	
	WECHAT_PAY_CONFIG_ERROR(80011,"微信配置失败","微信配置失败"), 
	
	CREATE_ORDER_ERROR(80012,"生成微信订单失败","生成微信订单失败"), 
	
	WECHAT_PAY_CALLBACK_SIGN_ERROR(80013,"微信支付回掉签名错误","微信支付回掉签名错误"),
	
	PAY_CALLBACK_ERROR(80014,"支付回调失败","支付回调失败"),

	WECHATPAY_CHECK_FAIL(80015,"微信支付校验失败","微信支付校验失败"),
	
	CALLBACK_APP_ERROR(80016,"支付回调失败","支付回调失败"),
	
	PAYORDER_ON_MONEY_VAIL(80017,"实际金额错误","实际金额错误"), 
	
	TOTAL_FEE_CHANGE(80018,"商品价格发生变化请重新购买","商品价格发生变化请重新购买"),
	
	USER_INFO_ERROR(80019,"获取用户信息失败","获取用户信息失败"),
	
	GET_WECHAT_ACCTOKEN_ERROR(90001,"获取微信token信息失败","获取微信token信息失败"), 
	
	WECHAT_PAY_REFLUN_ERROR(90008,"微信退款返回错误","微信退款返回错误"),
	
	WECHAT_PAY_REFLUN_AL(90009,"已经全额退款","已经全额退款"),
	
	
	
	SIGN_ERROR(100000,"签名错误","签名错误"),
	
	REFUND_ERROR(100001,"退款失败","退款失败"),
	
	USER_NULL(200001,"用户不存在","用户不存在"), 
	
	FORM_ID_LESS(200002,"formId不够","formId不够"), 
	
	CARD_CODE_ERROR(200003,"卡卷第三方接口错误","卡卷第三方接口错误"),
	
	WXARCODE_FAIL(200004,"获取二维码失败","获取二维码失败"), 
	
	
	
;



	private int code;
	private String msg;
	private String userMsg;

	private WechatExceptionType(int code, String msg, String userMsg) {
		this.code = code;
		this.msg = msg;
		this.userMsg = userMsg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getUserMsg() {
		return userMsg;
	}

}
 