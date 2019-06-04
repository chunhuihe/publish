package com.gym.wechat.inout.out.wechatuser; 

import java.io.Serializable;

/** 
 * @author huangjiangnan 
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月21日 下午5:17:39 
 * 类说明 
 */
public class MemberResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2958257047802904988L;

	private String token;
	
	private Long memberId;

	private Integer memberType;

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	
}
 