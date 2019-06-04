package com.gym.wechat.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(name = "wechat_user")
public class WechatUser implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	// 可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	// columns START
	// wechatUserId

	@Id
	@Column(name = "wechat_user_id")
	private Long wechatUserId;
	// wechatAppId
	@Column(name = "wechat_app_id")
	private String wechatAppId;
	// openId
	@Column(name = "open_id")
	private String openId;
	// 用户昵称
	@Column(name = "nickname")
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	@Column(name = "sex")
	private Integer sex;
	// 省份
	@Column(name = "province")
	private String province;
	// 城市
	@Column(name = "city")
	private String city;
	// 国家
	@Column(name = "country")
	private String country;
	// 头像
	@Column(name = "headimgurl")
	private String headimgurl;
	// 用户授权信息
	@Column(name = "privilege")
	private String privilege;
	// unionid
	@Column(name = "unionid")
	private String unionid;
	
	@Column(name = "member_id")
	private Long memberId;
	// 创建时间
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name="session_key")
	private String sessionKey;

	// columns END

	public WechatUser() {
	}

	public WechatUser(Long wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	public void setWechatUserId(Long value) {
		this.wechatUserId = value;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getWechatUserId() {
		return this.wechatUserId;
	}

	public String getWechatAppId() {
		return this.wechatAppId;
	}

	public void setWechatAppId(String value) {
		this.wechatAppId = value;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String value) {
		this.openId = value;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String value) {
		this.nickname = value;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer value) {
		this.sex = value;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String value) {
		this.province = value;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String value) {
		this.city = value;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String value) {
		this.country = value;
	}

	public String getHeadimgurl() {
		return this.headimgurl;
	}

	public void setHeadimgurl(String value) {
		this.headimgurl = value;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String value) {
		this.privilege = value;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setUnionid(String value) {
		this.unionid = value;
	}

	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date value) {
		this.createdTime = value;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public static WechatUser findByWechatAppIdAndOpenId(EntityManager em, String wechatAppId, String openId) {
		String hql = "from WechatUser where wechatAppId=:wechatAppId and openId=:openId";
		TypedQuery<WechatUser> query = em.createQuery(hql, WechatUser.class);
		query.setParameter("wechatAppId", wechatAppId);
		query.setParameter("openId", openId);
		List<WechatUser> list = query.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	public static long countAppIdAndCreateTime(EntityManager em, String wechatAppId, Date createdTime) {
		String hql="select count(*) where wechatAppId=:wechatAppId and createdTime<:createdTime";
		TypedQuery<Long> query=em.createQuery(hql, Long.class);
		query.setParameter("wechatAppId", wechatAppId);
		query.setParameter("createdTime", createdTime);
		return query.getSingleResult();
	}

	public static WechatUser findByWechatAppIdAndMemberId(EntityManager em, String wechatAppId, long memberId) {
		String hql="from WechatUser where wechatAppId=:wechatAppId and memberId=:memberId";
		TypedQuery<WechatUser> query=em.createQuery(hql,WechatUser.class);
		query.setParameter("wechatAppId", wechatAppId);
		query.setParameter("memberId", memberId);
		List<WechatUser> list=query.getResultList();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public static WechatUser findByMemberIdAndWechatAppIdAndOpenId(EntityManager em, long memberId, String wechatAppId, String openId) {
		String hql="from WechatUser where memberId=:memberId and wechatAppId=:wechatAppId and openId=:openId";
	    TypedQuery<WechatUser> query=em.createQuery(hql, WechatUser.class);
		query.setParameter("wechatAppId", wechatAppId);
		query.setParameter("memberId", memberId);
		query.setParameter("openId", openId);
		List<WechatUser> wechatUserList=query.getResultList();
		if(!wechatUserList.isEmpty()){
			return wechatUserList.get(0);
		}
		return null;
	}

}
