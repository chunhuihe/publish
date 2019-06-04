package com.gym.wechat.inout.out.wechatuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月8日 下午4:07:04 类说明
 */
public class WechatOpenIdResp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8270461555362757057L;
	
	
	
	@ApiModelProperty(notes = "用户唯一标识")
	private String openid; // 用户唯一标识
	@ApiModelProperty(notes = "会话密钥")
	@JsonIgnore
	private String session_key; // 会话密钥
	@ApiModelProperty(notes = "用户在开放平台的唯一标识符")
	private String unionid; // 用户在开放平台的唯一标识符。本字段在满足一定条件的情况下才返回。具体参看UnionID机制说明
	private String errcode;// 40029,
	private String errmsg;
	@ApiModelProperty(notes = "用户唯一标识")
	private Long memberId;
	@ApiModelProperty(notes = "用户角色:游客:2 会员:1")
	private Integer memberType;

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	@ApiModelProperty(notes = "token")
	private String token;
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


}