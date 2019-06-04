package com.gym.wechat.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HttpClient 请求参数 转化类
 * 
 * @author jarlun
 */
public class NameValuePairUtils {

	public static List<NameValuePair> get(Map<String, Object> params) {
		if (params == null || params.size() == 0) {
			return null;
		}
		Set<Entry<String, Object>> entrySet = params.entrySet();
		List<NameValuePair> result = new ArrayList<NameValuePair>(
				entrySet.size());
		for (Entry<String, Object> entry : entrySet) {
			result.add(get(entry.getKey(), String.valueOf(entry.getValue())));
		}
		return result;
	}

	public static NameValuePair get(String name, String value) {
		if (StringUtils.isBlank(name)) {
			throw new IllegalArgumentException("参数名不能为空");
		}
		return new BasicNameValuePair(name, value);
	}

}
