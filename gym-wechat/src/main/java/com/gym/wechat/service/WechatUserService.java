package com.gym.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.gym.common.exception.BaseException;
import com.gym.common.exception.CmsException;
import com.gym.common.inout.BaseResponse;
import com.gym.common.service.IDService;
import com.gym.common.util.GsonUtil;
import com.gym.common.util.HttpPoolUtil;
import com.gym.common.util.SignUtil;
import com.gym.wechat.common.exception.WechatExceptionType;
import com.gym.wechat.common.util.EmojiFilterUtils;
import com.gym.wechat.domain.PayWechatConfig;
import com.gym.wechat.domain.WechatUser;
import com.gym.wechat.domain.tCoach;
import com.gym.wechat.domain.tCoachInfo;
import com.gym.wechat.inout.in.wechatmessage.MemberWechatOpenIdRequest;
import com.gym.wechat.inout.in.wechatuser.WechatOpenIdRequest;
import com.gym.wechat.inout.out.wechatuser.MemberResp;
import com.gym.wechat.inout.out.wechatuser.WechatOpenIdResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

/**
 * @author huangjiangnan
 * @email huangjiangnanjava@163.com
 * @version 1.0
 * @since 2017年11月8日 下午4:00:18 类说明
 */
@Service
public class WechatUserService {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static final String USER_INFO_FORMAT = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

	public static final String WEIXIN_USER_INFO_FORMAT = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s";

	@Autowired
	private WechatConfigService wechatConfigService;

	@Autowired
	private IDService idService;

	@Autowired
	private EntityManager em;

	@Value("${member.api.url}")
	private String memberApiUrl;

	@Value("${sing.app.key}")
	private String memberApiKey;

	@Value("${coach.id.begin}")
	private String begin;

	@Value("${coach.id.end}")
	private String end;

	@Transactional
	public WechatOpenIdResp getUserInfo(WechatOpenIdRequest request) {
		WechatOpenIdResp resp = new WechatOpenIdResp();
		try {
			String url = null;
			PayWechatConfig config = this.wechatConfigService.getWechatPayConfig(request.getAppId());
			if (config == null) {
				throw new BaseException(WechatExceptionType.PAY_CONFIG_NULL);
			}
			url = String.format(USER_INFO_FORMAT, config.getWechatAppId(), config.getWechatProKey(),
					request.getJsCode());
			logger.info("获取小程序用户信息url:{}", url);
			String json = HttpPoolUtil.doGetSSL(url);
			logger.info("返回参数：{}", json);
			resp = JSON.parseObject(json, WechatOpenIdResp.class);
			if (resp.getErrcode() != null) {
				throw new BaseException(500, resp.getErrcode() + "|" + resp.getErrmsg());
			}
			MemberResp memberResp = this.addOrUpdateWechatUser(config.getWechatAppId(), request, resp.getOpenid(),
					resp.getUnionid(), resp.getSession_key());
			if (memberResp != null) {
				resp.setToken(memberResp.getToken());
				resp.setMemberId(memberResp.getMemberId());
				resp.setMemberType(memberResp.getMemberType());
			} else {
				throw new BaseException(WechatExceptionType.USER_INFO_ERROR);
			}
			return resp;

		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取用户信息失败:{}", e);
			throw new BaseException(WechatExceptionType.USER_INFO_ERROR);
		}
	}

	public MemberResp addOrUpdateWechatUser(String wechatAppId, WechatOpenIdRequest request, String openId,
			String unionid, String sessionKey) {
		MemberResp resp = null;
		WechatUser user = WechatUser.findByWechatAppIdAndOpenId(em, wechatAppId, openId);
		if (user == null) {
			resp = this.getMemberId(request, openId, unionid, wechatAppId, null);
			user = new WechatUser();
			user.setWechatUserId(this.idService.generate());
			BeanUtils.copyProperties(request, user);
			user.setNickname(EmojiFilterUtils.filterEmoji(user.getNickname()));
			user.setWechatAppId(wechatAppId);
			user.setUnionid(unionid);
			if (resp != null) {
				user.setMemberId(resp.getMemberId());
			}
			user.setSessionKey(sessionKey);
			user.setOpenId(openId);
			user.setCreatedTime(new Date());
			em.persist(user);
		} else {
			BeanUtils.copyProperties(request, user);
			user.setWechatAppId(wechatAppId);
			user.setUnionid(unionid);
			user.setSessionKey(sessionKey);
			if (user.getMemberId() == null || user.getMemberId() < 1) {
				resp = this.getMemberId(request, openId, unionid, wechatAppId, null);
			} else {
				logger.info("用户id:{}", user.getMemberId());
				resp = this.getMemberId(request, openId, unionid, wechatAppId, user.getMemberId());
			}
			if (resp != null) {
				user.setMemberId(resp.getMemberId());
			}
			em.merge(user);
		}
		return resp;

	}

	@SuppressWarnings("unchecked")
	public MemberResp getMemberId(WechatOpenIdRequest request, String openId, String unionid, String wechatAppId,
			Long memberId) {
		if (request.getNickname() == null) {
			throw new BaseException(121212, "昵称不存在");
		}
		MemberWechatOpenIdRequest req = new MemberWechatOpenIdRequest();
		BeanUtils.copyProperties(request, req);
		req.setOpenId(openId);
		req.setUnionid(unionid);
		req.setWechatAppId(wechatAppId);
		req.setMemberId(memberId);
		req.setSource(request.getSource());
		try {
			req.setSign(SignUtil.getSign(req, memberApiKey));
		} catch (Exception e1) {
			logger.error("用户签名失败：{}", e1);
		}
		try {
			String json = JSON.toJSONString(req);
			logger.info("调用用户模块参数：{}", json);
			String body = HttpPoolUtil.httpPostByString(memberApiUrl, json);
			logger.info("调用用户模块返回结果：{}", body);
			BaseResponse<MemberResp> resp = GsonUtil.fromJson(body, BaseResponse.class, MemberResp.class);
			if (resp != null && resp.isSuccess()) {
				return resp.getData();
			}else{
				throw new BaseException(WechatExceptionType.USER_INFO_ERROR);
			}

		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			logger.error("获取用户信息失败:{}", e);
			throw new BaseException(WechatExceptionType.USER_INFO_ERROR);
		}
	}


    public void getCoach() throws InterruptedException {

		String ids="";
		 for (int i=Integer.parseInt(begin);i>Integer.parseInt(end);i--) {
		 	ids=String.valueOf(i);
			 String respJson = HttpPoolUtil.doGetSSL("https://api3.koudaifit.com/fit/user/" + ids + "/coach/info");
             try {


			 JSONObject jsonObjectCoach= JSON.parseObject(respJson);
			 tCoachInfo info =new tCoachInfo();
			 if(jsonObjectCoach.get("phone").toString().isEmpty())
			 {
			 	continue;
			 }
			 info.setId(Integer.parseInt(jsonObjectCoach.get("id").toString()));
			 info.setPhone(jsonObjectCoach.get("phone").toString());
			 info.setToken(jsonObjectCoach.get("token").toString());
			 info.setUserName(jsonObjectCoach.get("userName").toString());
			 info.setAddress(jsonObjectCoach.get("address").toString());
			 info.setGymName(jsonObjectCoach.get("gymName").toString());
			 info.setAvatar(jsonObjectCoach.get("avatar").toString());
			 info.setRecordDate(jsonObjectCoach.get("recordDate").toString());
			 if(jsonObjectCoach.get("vipEndtime")!=null) {
				 info.setVipEndtime(jsonObjectCoach.get("vipEndtime").toString());
				 info.setVipStarttime(jsonObjectCoach.get("vipStarttime").toString());
			 }
				 info.setLatitude(Double.parseDouble(jsonObjectCoach.get("latitude").toString()));
        info.setLongitude(Double.parseDouble(jsonObjectCoach.get("longitude").toString()));
			 try {
				 info.insert(em);
			 }catch (Exception ex){}
            try {
				String respJson2 = HttpPoolUtil.doGetSSL("https://api3.koudaifit.com/fit/userV2",info.getToken());
				JSONArray jsonArray= JSON.parseArray(respJson2);
				for(int j=0;j<jsonArray.size();j++) {

						JSONObject coachjs = jsonArray.getJSONObject(j);
					if(coachjs.get("type").toString().equals("phone")) {
						tCoach coach = new tCoach();
						coach.setUserId(Integer.parseInt(coachjs.get("userId").toString()));
						coach.setAppId(Integer.parseInt(coachjs.get("appId").toString()));
						coach.setPwd(coachjs.get("pwd").toString());
						coach.setUuId(coachjs.get("uuId").toString());
						coach.setType(coachjs.get("type").toString());
						coach.insert(em);
					}
				}

			}
			catch (Exception ex){}
			 }catch (Exception ex)
			 {
			 	continue;
			 }
			 Thread.currentThread().sleep(500);//毫秒
		 }




    }



	@Transactional
	public void getCoach(Integer id) throws InterruptedException {


			String respJson = HttpPoolUtil.doGetSSL("https://api3.koudaifit.com/fit/user/" + id.toString() + "/coach/info");
			try {


				JSONObject jsonObjectCoach= JSON.parseObject(respJson);
				tCoachInfo info =new tCoachInfo();
				if(jsonObjectCoach.get("phone").toString().isEmpty())
				{
					 return;
				}
				info.setId(Integer.parseInt(jsonObjectCoach.get("id").toString()));
				info.setPhone(jsonObjectCoach.get("phone").toString());
				info.setToken(jsonObjectCoach.get("token").toString());
				info.setUserName(jsonObjectCoach.get("userName").toString());
				info.setAddress(jsonObjectCoach.get("address").toString());
				info.setGymName(jsonObjectCoach.get("gymName").toString());
				info.setAvatar(jsonObjectCoach.get("avatar").toString());
				info.setRecordDate(jsonObjectCoach.get("recordDate").toString());
				if(jsonObjectCoach.get("vipEndtime")!=null) {
					info.setVipEndtime(jsonObjectCoach.get("vipEndtime").toString());
					info.setVipStarttime(jsonObjectCoach.get("vipStarttime").toString());
				}
				info.setLatitude(Double.parseDouble(jsonObjectCoach.get("latitude").toString()));
				info.setLongitude(Double.parseDouble(jsonObjectCoach.get("longitude").toString()));
				try {
					info.insert(em);
				}catch (Exception ex){}
				try {
					String respJson2 = HttpPoolUtil.doGetSSL("https://api3.koudaifit.com/fit/userV2",info.getToken());
					JSONArray jsonArray= JSON.parseArray(respJson2);
					for(int j=0;j<jsonArray.size();j++) {

						JSONObject coachjs = jsonArray.getJSONObject(j);
						if(coachjs.get("type").toString().equals("phone")) {
							tCoach coach = new tCoach();
							coach.setUserId(Integer.parseInt(coachjs.get("userId").toString()));
							coach.setAppId(Integer.parseInt(coachjs.get("appId").toString()));
							coach.setPwd(coachjs.get("pwd").toString());
							coach.setUuId(coachjs.get("uuId").toString());
							coach.setType(coachjs.get("type").toString());
							coach.insert(em);
						}
					}

				}
				catch (Exception ex){}
			}catch (Exception ex)
			{

			}




	}




}
