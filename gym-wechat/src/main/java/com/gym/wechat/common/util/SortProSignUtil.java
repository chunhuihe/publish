package com.gym.wechat.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;


/**
 * KgSignUtil.java
 * 
 * @author janehuang
 * @version 1.0
 * @创建时间：2015年9月8日 上午11:04:44
 */
public class SortProSignUtil {

	private final static Logger logger = LoggerFactory.getLogger(SortProSignUtil.class);

	private final static String LINK = "&";

	private final static String EQUAL = "=";

	public static boolean check(Object obj, String sign, String appKey) {
		logger.info("input sign:{}", sign);
		try {
			String reuslt = getSign(obj, appKey);
			logger.info("my sign:{}", sign);
			return sign.equals(reuslt);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getSign(Object obj, String appKey)  {
		String st = null;
		try {
			st = getParam(obj);
		} catch (Exception e) {
			 e.printStackTrace();
			 return null;
		}
		String key = st + appKey;
		String sign = DigestUtils.md5Hex(key);
		return sign;
	}

	public static String getUrl(String baseUrl, Object obj, String appKey)  {
		String st;
		try {
			st = getParam(obj);
		} catch (Exception e) {
			e.printStackTrace();
		    return null;
		}
		String url = baseUrl + "?" + st;
		String key = st + appKey;
		String sign = DigestUtils.md5Hex(key);
		return string2Utf(url + "&sign=" + sign);
	}

	public static String getParam(Object obj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer params = new StringBuffer();
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		int num = 0;
		for (PropertyDescriptor pd : propertyDescriptors) {
			String proName = pd.getName();
			System.out.println(proName);
			if (!"class".equals(proName) && !"sign".equals(proName)&&!"sign_type".equals(proName)) {
				Method method = pd.getReadMethod();
				Object value = method.invoke(obj);
				if (value != null) {
					if (value instanceof Integer) {
						Integer i = (Integer) value;
						if (i == 0) {
							continue;
						}
					}
					if (value instanceof Long) {
						Long longValue = (Long) value;
						if (longValue == 0) {
							continue;
						}
					}

					if (num != 0) {
						params.append(LINK);
					}
					if (value instanceof Double) {
						String fee = getPrettyNumber((Double) value);
						params.append(proName).append(EQUAL).append(fee);
					} else {
						params.append(proName).append(EQUAL).append(value);
					}
					num++;
				}
			}
		}
		String st = params.toString();
		return st;
	}

	public static String getPrettyNumber(double number) {
		return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
	}

	public static String string2Utf(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {
				// 汉字范围 \u4e00-\u9fa5 (中文)
				try {
					result += URLEncoder.encode(Character.toString(str.charAt(i)), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
	

}
