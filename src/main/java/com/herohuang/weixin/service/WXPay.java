package com.herohuang.weixin.service;

import com.herohuang.weixin.util.OrderUtil;
import com.herohuang.weixin.util.MD5;
import com.herohuang.weixin.util.MD5Util;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * 调起微信支付的参数签名类
 * @author herohuang
 * 
 */
public class WXPay {
    /**
     * 调起微信支付的参数签名
     * @param appid
     * @param appKey
     * @param prepay_id
     * @return
     */
	public static String createPackageValue(String appid, String appKey, String prepay_id)  {
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("appId", MD5.GetMD5String(appid));
		map.put("partnerId", MD5.GetMD5String("1230000109"));
		map.put("prepayId", MD5.GetMD5String(prepay_id));
        map.put("nonceStr", MD5.GetMD5String(String.valueOf(new Random().nextInt(10000))));
        map.put("timeStamp", MD5.GetMD5String(OrderUtil.GetTimestamp()));
		map.put("package", "Sign=WXPay");
		map.put("sign", createSign(map, appKey));
		return JSONObject.fromObject(map).toString();
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	private static String createSign(SortedMap<String, String> packageParams, String AppKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		return sign;
	}
}
