package com.gym.wechat.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "wechat_form")
public class WechatForm implements java.io.Serializable{
	
	private static final long serialVersionUID = 5454155825314635342L;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//formWechatId
	@Id
	@Column(name = "form_wechat_id")
	private Long formWechatId;
	//微信小程序appId
	@Column(name = "wechat_app_id")
	private String wechatAppId;
	//openId
	@Column(name = "open_id")
	private String openId;
	//表单id
	@Column(name = "form_id")
	private String formId;
	//支付id
	@Column(name = "prepay_id")
	private String prepayId;
	//创建时间
	@NotNull 
	@Column(name = "created_time")
	private Date createdTime;
	//使用状态
	@Column(name = "use_status")
	private int useStatus;
	//使用次数
	@Column(name = "use_num")
	private int useNum;
	//columns END


	
	
	
	public Long getFormWechatId() {
		return this.formWechatId;
	}
	
	

	public int getUseNum() {
		return useNum;
	}



	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}



	public int getUseStatus() {
		return useStatus;
	}



	public void setUseStatus(int useStatus) {
		this.useStatus = useStatus;
	}



	public void setFormWechatId(Long value) {
		this.formWechatId = value;
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
	
	
	public String getFormId() {
		return this.formId;
	}
	
	public void setFormId(String value) {
		this.formId = value;
	}
	
	
	public String getPrepayId() {
		return this.prepayId;
	}
	
	public void setPrepayId(String value) {
		this.prepayId = value;
	}
	
	
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public void setCreatedTime(Date value) {
		this.createdTime = value;
	}

	public static WechatForm findOneByWechatAppIdAndOpenId(EntityManager em, String wechatAppId, String openId) {
		Date createdTime=new Date(System.currentTimeMillis()-1000*60*60*23);
		String hql="from WechatForm where wechatAppId=:wechatAppId and  openId=:openId and useStatus=0 and createdTime>:createdTime order by createdTime asc";
		TypedQuery<WechatForm> query=em.createQuery(hql,WechatForm.class);
		query.setParameter("wechatAppId", wechatAppId);
		query.setParameter("openId", openId);
		query.setParameter("createdTime", createdTime);
		query.setFirstResult(1);
		query.setMaxResults(1);
		List<WechatForm> list=query.getResultList();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public static int updateFormUseStatus(EntityManager em, Long formWechatId) {
		String hql="update WechatForm  set useStatus=1,useNum=useNum+1 where formWechatId=:formWechatId  ";
		Query query=em.createQuery(hql);
		query.setParameter("formWechatId", formWechatId);
		return query.executeUpdate();
	}
	
	
	public static int updateFormUseNum(EntityManager em, Long formWechatId) {
		String hql="update WechatForm set useNum=useNum+1 where formWechatId=:formWechatId  ";
		Query query=em.createQuery(hql);
		query.setParameter("formWechatId", formWechatId);
		return query.executeUpdate();
	}
	

	
	
}

