package com.gym.wechat.common.constant; 
/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年9月18日 下午3:19:26 
 * 类说明 
 */
public class AlipayConstant {

	//超时时间
	public static final String REQUEST_TIME_OUT="300m";
	
	public static String TRADE_FINISHED	="TRADE_FINISHED";//交易完成	true（触发通知）
	
	public static String TRADE_SUCCESS="TRADE_SUCCESS";	//支付成功	true（触发通知）
	
	public static String WAIT_BUYER_PAY	="WAIT_BUYER_PAY";	//交易创建	false（不触发通知）
	
	public static String TRADE_CLOSED	="WAIT_BUYER_PAY";	//交易关闭	true（触发通知）
}
 