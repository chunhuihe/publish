package com.gym.wechat.common.constant;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月18日 下午3:47:14 类说明
 */
public interface PayType {

	// 支付宝支付
	public static final int ALIPAY = 2;

	// 微信支付
	public static final int WECHATPAY = 1;
	
	
	// 微信小程序支付
	public static final int JSAPI_PAY = 12;
	
	
	//测试默认一分钱
	public static final double DEFAULT_TEST_MONEY=0.01;
	
	
	public static final String JSAPI_TRADE="JSAPI";
	
	
	

}
