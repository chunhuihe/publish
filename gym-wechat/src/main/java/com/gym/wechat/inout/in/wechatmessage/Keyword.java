package com.gym.wechat.inout.in.wechatmessage; 
/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月14日 下午4:03:05 
 * 类说明 
 */
public class Keyword {

	private String value;
	
	private String color="#173177";

	
	
	public Keyword() {
		super();
	}

	public Keyword(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}

	public Keyword(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
 